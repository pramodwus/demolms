package com.qbis.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qbis.model.Product;
import com.qbis.model.Response;
import com.qbis.model.ServiceResult;
import com.qbis.model.ServiceResult.StatusCode;
import com.qbis.services.ProductService;

/**
 * This is used for performing all operations related to product api.
 * 
 * @author Ankur Kumar
 *
 */
@RestController
@RequestMapping(value = "/api/product/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ProductWebService {
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(QbisWebService.class);
	/**
	 * Instance of {@link productService}
	 */
	@Autowired
	private ProductService productService;

	/**
	 * This method is used for getting product list.
	 * 
	 * @param limit
	 * @param offset
	 * @return {@link Response}
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/productlist", method = RequestMethod.GET)
	public Object getProductList(@RequestHeader(value = "Authorization", required = false) String token,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset) {
		logger.debug("Inside ProductWebService in getProductList api :::::::");
		return new ServiceResult(StatusCode.SUCCESS, productService.getProductList(offset, limit, token));
	}

	/**
	 * This method is used for getting product details based on id.
	 * 
	 * @param token
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/productlist/{productId}", method = RequestMethod.GET)
	public Object getProduct(@RequestHeader(value = "Authorization", required = false) String token,
			@PathVariable Integer productId) {
		logger.debug("Inside ProductWebService in getProductList api :::::::");
		Product product = productService.getProduct(token, productId);
		return new ServiceResult(StatusCode.SUCCESS, product);
	}

	/**
	 * This method is used for placing the product order.
	 * 
	 * @param token
	 *            {@link String}
	 * @param orderId
	 *            {@link Long}
	 * @return {@link ServiceResult}
	 */
	@RequestMapping(value = "/place/{orderId}/", method = RequestMethod.GET)
	public Object placeProductOrder(@RequestHeader("Authorization") String token,
			@PathVariable("orderId") Long orderId) {
		logger.debug("Inside ProductWebService in placeProductOrder api ::::::: orderId = " + orderId);
		boolean placed = productService.placeProductOrder(token, orderId);
		if (placed) {
			return new ServiceResult(StatusCode.SUCCESS, placed);
		}
		return new ServiceResult(StatusCode.ERROR, placed);
	}
}

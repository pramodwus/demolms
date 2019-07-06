<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- Message Dialogs to be used in application  -->
<!-- Success dialog -->
<div class="modal fade modal-success" id="successdialog">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title">
					<i class="icon fa fa-check"></i>&nbsp;<spring:message code="lbl.success" text="Success"/>!
				</h4>
			</div>
			<div class="modal-body">
				<p></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-outline pull-right"
					data-dismiss="modal"><spring:message code="lbl.close" text="Close"/></button>
			</div>
		</div>
	</div>
</div>

<!-- Warning dialog -->
<div class="modal fade modal-warning" id="warningdialog">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title">
					<i class="icon fa fa-warning"></i>&nbsp;<spring:message code="lbl.warning" text="Warning"/>!
				</h4>
			</div>
			<div class="modal-body">
				<p></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-outline pull-right"
					data-dismiss="modal"><spring:message code="lbl.close" text="Close"/></button>
			</div>
		</div>
	</div>
</div>

<!-- Error dialog -->
<div class="modal fade modal-danger" id="errordialog">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title">
					<i class="icon fa fa-ban"></i>&nbsp;<spring:message code="lbl.error" text="Error"/>!
				</h4>
			</div>
			<div class="modal-body">
				<p></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-outline pull-right"
					data-dismiss="modal"><spring:message code="lbl.close" text="Close"/></button>
			</div>
		</div>
	</div>
</div>
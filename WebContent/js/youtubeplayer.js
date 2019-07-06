var checkPromoURL = function(promoUrl){
		var urlStatus=false;		
		var myRegExp=/^(?:https?:\/\/)?(?:www\.)?(?:youtu\.be\/|youtube\.com\/(?:embed\/|v\/|watch\?v=|watch\?.+&v=))((\w|-){11})(?:\S+)?$/;
		if (myRegExp.test(promoUrl)) {
			urlStatus=true;
		}
		return urlStatus;
	}	

var tag;
var k=0;
	var videoId;
	// 1. function for playing video.
	function playVideo(videoUrl) {
		console.log("===playVideo=====");		
		videoId = "";
		var promoVideoUrl = videoUrl.trim();
		if (checkPromoURL(promoVideoUrl)) {
			// 2. This code loads the IFrame Player API code asynchronously.
			tag = document.createElement('script');
			tag.src = "https://www.youtube.com/iframe_api";
			var firstScriptTag = document.getElementsByTagName('script')[0];
			firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
			var isV = promoVideoUrl.indexOf("v=");
			videoId = (isV != -1) ? (promoVideoUrl.split('v=')[1])
					: (promoVideoUrl
							.substring(promoVideoUrl.lastIndexOf("/") + 1));
			var indexofamp = videoId.indexOf("&");
			if(indexofamp!=-1){
				videoId = videoId.substring(0,videoId.indexOf("&"));
			}
          console.log("===videoId====="+videoId);
			  if(k==1){
				  onYouTubeIframeAPIReady();  
			  } 
			  
		}
	}
	// 3. This function creates an <iframe> (and YouTube player)
	//    after the API code downloads.
	
	function onYouTubeIframeAPIReady() {
		k=1;
		console.log("===onYouTubeIframeAPIReady====="+videoId);		
		player = new YT.Player('promoVideo', {
			height : '250',
			width : '400',
			videoId : videoId,
			events : {
				'onReady' : onPlayerReady,
				'onStateChange' : onPlayerStateChange				
			},
			enablejsapi:1
		});
	}
	
	var done = false;
	// 4. The API will call this function when the video player is ready.
	function onPlayerReady(event) {
		//event.target.playVideo();
		event.target.stopVideo();
		done = true;
	}

	// 5. The API calls this function when the player's state changes.
	//    The function indicates that when playing a video (state=1),
	//    the player should play for six seconds and then stop.
	
	function onPlayerStateChange(event) {
		if (event.data == YT.PlayerState.PLAYING && !done) {
			setTimeout(stopVideo, 6000);
			done = true;
		}
	}
	function stopVideo() {
		player.stopVideo();
	}
	
	
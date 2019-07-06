// The Client ID obtained from the Google Developers Console. Replace with your
// own Client ID.
var clientId = "587678799583-s4p3582d476sdldcfi55a2fftomce7bg.apps.googleusercontent.com";

// Scope to use to access user's photos.
var scope = [ 'https://www.googleapis.com/auth/drive' ];

var oauthToken;

function onApiLoad() {
	gapi.load('auth', {
		'callback' : onAuthApiLoad
	});
}

function onAuthApiLoad() {
	window.gapi.auth.authorize({
		'client_id' : clientId,
		'scope' : scope,
		'immediate' : false
	}, handleAuthResult);
}

function handleAuthResult(authResult) {
	if (authResult && !authResult.error) {
		oauthToken = authResult.access_token;
		/**
		 * @summary This is used for pushing the message for parent window.
		 */
		window.parent.window.postMessage({
			'func' : 'alertMyMessage',
			'params' : [ oauthToken ]
		}, absPath + 'listuploadcontent');
	}
}

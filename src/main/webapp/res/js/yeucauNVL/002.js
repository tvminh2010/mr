$(document).ready(function() {
	var stompClient = null;
	connect();
/*	function setConnected(connected) {
	    $("#connect").prop("disabled", connected);
	    $("#disconnect").prop("disabled", !connected);
	    if (connected) {
	        $("#conversation").show();
	    }
	    else {
	        $("#conversation").hide();
	    }
	    $("#greetings").html("");
	}
*/
	function connect() {
	    var socket = new SockJS('/mr/gs-guide-websocket');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	      //  setConnected(true);
	        console.log('Connected: ' + frame);
	        stompClient.subscribe('/topic/greetings', function (greeting) {
	            showGreeting();
	        });
	    });
	}

	function disconnect() {
	    if (stompClient != null) {
	        stompClient.disconnect();
	    }
	    setConnected(false);
	    console.log("Disconnected");
	}

	function sendName() {
	    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
	}

	function showGreeting() {
		
		var id = $("#workorder").val();
		window.open("/mr/yeucauNVL/input?woid="+id,"_self");
		
	}


});

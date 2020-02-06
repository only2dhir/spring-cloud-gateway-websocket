var ws;
function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	} else {
		$("#conversation").hide();
	}
	$("#greetings").html("");
}

function connect() {
	//connect to stomp where stomp endpoint is exposed
	var socket = new WebSocket("ws://localhost:8080/websocket/greeting");
	ws = Stomp.over(socket);

	ws.connect({}, function(frame) {
		ws.subscribe("/topic/errors", function(message) {
			alert("Error " + message.body);
		});

		ws.subscribe("/topic/reply", function(message) {
			showGreeting(message.body);
		});
	}, function(error) {
		alert("STOMP error " + error);
	});
}

function disconnect() {
	if (ws != null) {
		ws.close();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendName() {
	var data = JSON.stringify({
		'name' : $("#name").val()
	})
	ws.send("/app/message", {}, data);
}

function showGreeting(message) {
	$("#greetings").append("<tr><td> " + message + "</td></tr>");
}

$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#connect").click(function() {
		connect();
	});
	$("#disconnect").click(function() {
		disconnect();
	});
	$("#send").click(function() {
		sendName();
	});
});

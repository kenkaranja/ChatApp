<html>
<head>
    <h2>Hello World!</h2>
    <script type="text/javascript">
        var socket;

        function connect() {
            var uname = document.getElementById("sender").value;
            socket = new WebSocket("ws://localhost:8888/Chat/chatting/" + uname);
            socket.onmessage = onMesage;
        }

        WebSocket.onclose = function (event) {
            onClose(event)
        }

        WebSocket.onerror = function (event) {
            onError(event)
        };
        WebSocket.function
        onClose(event)
        {
            alert(event.data)
        }

        function onMesage(event) {
            alert(event.data);
        }

        function sendMsg() {
            socket.send(JSON.stringify({
                "to": document.getElementById("receiver").value,
                "message": document.getElementById("msg").value,
                "from": document.getElementById("sender").value
            }));
        }


        function onError(event) {
            alert(event.data)
        }


    </script>
</head>

<body>

Enter Sender: <input type="text" id="sender">
<input type="button" value="Connect" onclick="connect()"><br>
Enter Receiver <input type="text" id="receiver"><br>
Message<textarea id="msg" rows="10" cols="10"></textarea>
<input type="button" value="Send Message" onclick="sendMsg()">
</body>
</html>

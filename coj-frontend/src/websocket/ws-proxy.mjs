import { WebSocketServer } from "ws";
import {
  WebSocketMessageReader,
  WebSocketMessageWriter,
} from "vscode-ws-jsonrpc";

import pkg from 'vscode-jsonrpc/lib/common/api.js';

const { StreamMessageReader, StreamMessageWriter } = pkg;

const CLIENT_PORT = process.env.CLIENT_PORT || 3002;
const CLIENT_HOST = process.env.CLIENT_HOST || 'localhost';

const server = new WebSocketServer({ port: 3002 });

server.on('connection', (socket) => {
  const clientSocket = new Socket();

  clientSocket.connect(CLIENT_PORT, CLIENT_HOST, () => {
    const socketReader = new WebSocketMessageReader(socket);
    const socketWriter = new WebSocketMessageWriter(socket);
    const serverReader = new StreamMessageReader(clientSocket);
    const serverWriter = new StreamMessageWriter(clientSocket);

    socketReader.listen((message) => {
      serverWriter.write(message);
    });

    serverReader.listen((message) => {
      socketWriter.write(message);
    });

    clientSocket.on('close', () => {
      socket.close();
    });

    socket.on('close', () => {
      clientSocket.end();
    });

    clientSocket.on('error', (err) => {
      console.error(`Client socket error: ${err}`);
      socket.close();
    });

    socket.on('error', (err) => {
      console.error(`WebSocket error: ${err}`);
      clientSocket.end();
    });
  });
});
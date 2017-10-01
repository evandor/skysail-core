import { Observable } from 'rxjs/Observable';
import { Injectable } from '@angular/core';

import * as io from 'socket.io-client';

@Injectable()
export class ChatService {

  private url = 'ws://localhost:8080/websocket';
  private socket;

  sendMessage(message) {
    this.socket.emit('add-message', message);
    console.log("MESSAGE SENT");
  }

  getMessages() {
    let observable = new Observable(observer => {
      this.socket = io(this.url);
      this.socket.on('message', (data) => {
        observer.next(data);
      });
      return () => {
        this.socket.disconnect();
      }
    })
    return observable;
  }

}
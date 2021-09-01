import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MeetingRoom } from 'src/app/shared/models/meeting.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private readonly API = "http://localhost:8080/meeting";

  private _meetingRoom: MeetingRoom;
  private _edit: boolean = false;

  constructor(
    private httpClient: HttpClient
  ) { }

  private createMeetingRoom(room: MeetingRoom): Observable<Object> {
    return this.httpClient.post(`${this.API}/create`, room);
  }

  findByIdMeetingRoom(id: number): Observable<MeetingRoom> {
    return this.httpClient.get<MeetingRoom>(`${this.API}/${id}`);
  }

  listAllMeetingRoom(): Observable<MeetingRoom[]> {
    return this.httpClient.get<MeetingRoom[]>(`${this.API}/list`);
  }

  private updateMeetingRoom(id: number, room: MeetingRoom): Observable<Object> {
    return this.httpClient.put(`${this.API}/update/${id}`, room);
  }

  deleteMeetingRoom(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.API}/delete/${id}`, { responseType: 'text' });
  }

  get room(): MeetingRoom {
    return this._meetingRoom;
  }

  set room(value: MeetingRoom) {
    this._edit = true;
    this._meetingRoom = value;
  }

  get edit(): boolean {
    return this._edit;
  }

  set edit(value: boolean) {
    this._edit = value;
  } 

  onSave(room: MeetingRoom) {
    if(this._edit) {
      this.updateMeetingRoom(room.id, room).subscribe({
        next: () => console.log("Room Atualizada!"),
        error: err => console.log(err)
      })
    } else {
      this.createMeetingRoom(room).subscribe({
        next: () => console.log("Room Salva!"),
        error: err => console.log(err)
      })
    }
  }

}

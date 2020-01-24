import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  private firstnumber:number=null;
  private secondnumber:number=null;
  private result:number=null;
public getAdd():void
{
  this.result=this.firstnumber+this.secondnumber;
  
}
public getSub():void
{
  this.result=this.firstnumber-this.secondnumber;
}
public getMul():void
{
  this.result=this.firstnumber*this.secondnumber;
}
public getDiv():void
{
  this.result=this.firstnumber/this.secondnumber;
}

}

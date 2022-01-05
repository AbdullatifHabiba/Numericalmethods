import { Component } from '@angular/core';
import{HttpClient, HttpErrorResponse}from'@angular/common/http';
import { Point } from './Point';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'project';
  equations:string="";
  drop:any;
  NonDrop:any;
  ArrOfEquations:any=[];
  initial:any=[];

  initialGuess:string="";
  numberOfIteration:number=0;
  RelativeError:number=0;
  result:any;
  Gauess:boolean=false;
  Jordan:boolean=false;
  Jacobi:boolean=false;
  sediel:boolean=false;
  LU_decomposition:boolean=false;
  precisionOfResult:number=0;

  typeOfLU:string="";

  constructor(private http:HttpClient){}
  color="red";color2="black";
  points:Array<Point>=[]
ngOnInit(): void {
  this.http
  .get('http://localhost:8080/method/points', {

    observe: 'response',
  })
  .subscribe(
    (response) => {
      this.result = response.body;
      this.points = this.result;
      console.log(this.points)
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  );
}
  GauessEliminationEq(equations:string,precision:number)
  {
    this.http.get('http://localhost:8080/method/GaussElimination',{
      params:{
        Matrix:equations,
        Precision:precision
      },
      observe:'response'
  }).subscribe(response=>{
    this.result=response.body;
   })
  }
  GauessJordanEq(equations:string,precision:number)
  {
    this.http.get('http://localhost:8080/method/GaussJordan',{
      params:{
        Matrix:equations,
        Precision:precision
      },
      observe:'response'
  }).subscribe(response=>{
    this.result=response.body;
   })
  }
  LUDecomposition(equations:string,Type:string,precision:number)
  {
    this.http.get('http://localhost:8080/method/LUDecomposition',{
      params:{
        Matrix:equations,
        type:Type,
        Precision:precision
      },
      observe:'response'
  }).subscribe(response=>{
    this.result=response.body;
   })
  }


  jacobiEq(equations:string,numbOfIter:number,intial:number[],relativeErr:number,precision:number)
  {
    this.http.get('http://localhost:8080/method/Jacobi',{
      params:{
        Matrix:equations,
        Intial:intial,
        numbOfIter:numbOfIter,
        relativeErr:relativeErr,
        Precision:precision
      },
      observe:'response'
  }).subscribe(response=>{
    this.result=response.body;
   })
  }
  sedeil_Eq(equations:string,numbOfIter:number,intial:number[],relativeErr:number,precision:number)
  {
    this.http.get('http://localhost:8080/method/Seidel',{
      params:{
        Matrix:equations,
        Intial:intial,
        numbOfIter:numbOfIter,
        relativeErr:relativeErr,
        Precision:precision
      },
      observe:'response'
  }).subscribe(response=>{
    this.result=response.body;
    console.log(this.result);
   })
  }

///////////////////////////////////////////////////
toggle1 = false;
toggle2=false;
toggle3=false;
toggle4=false;
toggle5=false;
toggle6=false;

  submitForm(){
   this.toggle6=true;
    this.ArrOfEquations=this.equations;
    this.initial=this.initialGuess.split(",");

    if(this.Gauess)
    {
      this. GauessEliminationEq(this.ArrOfEquations,this.precisionOfResult);
    }
    else if(this.Jordan)
    {
      this.GauessJordanEq(this.ArrOfEquations,this.precisionOfResult);
    }
    else if(this.LU_decomposition)
    {
          this.LUDecomposition(this.ArrOfEquations,this.typeOfLU,this.precisionOfResult);
    }
    else if(this.Jacobi)
    {
      this. jacobiEq(this.ArrOfEquations,this.numberOfIteration,this.initial,this.RelativeError,this.precisionOfResult);
    }
    else{
         this.sedeil_Eq(this.ArrOfEquations,this.numberOfIteration,this.initial,this.RelativeError,this.precisionOfResult);
    }
  }

  dropList1()
  {
    this.setToggle(true,false,false,false,false,false);
    this.toggle1=true;
    this.drop=false;
    this.NonDrop=true;
    this.Gauess=true;
    this.result="";
    this.typeOfLU="";

  }

  dropList2(){
    this.setToggle(false,true,false,false,false,false);
    this.NonDrop=false;
    this.drop=false;
    this.NonDrop=true;
    this.Jordan=true;
    this.result="";
    this.typeOfLU="";

  }
  dropList3()
  {
    this.setToggle(false,false,true,false,false,false);
    this.drop=false;
    this.NonDrop=true;
    this.LU_decomposition=true;
    this.result="";
  }
  dropList4()
  {
    this.setToggle(false,false,false,true,false,false);
    this.drop=true;
    this.NonDrop=true;
    this.sediel=true;
    this.result="";
    this.typeOfLU="";

  }
  dropList5()
  {
    this.setToggle(false,false,false,false,true,false);
    this.drop=true;
    this.NonDrop=true;
    this.Jacobi=true;
    this.result="";
    this.typeOfLU="";

  }
  dropList6()
  {
    this.toggle6=false;
  }
  setToggle(t1:boolean,t2:boolean,t3:boolean,t4:boolean,t5:boolean,t6:boolean){
    this.toggle1=t1;
    this.toggle2=t2;
    this.toggle3=t3;
    this.toggle4=t4;
    this.toggle5=t5;
    this.toggle6=t6;
  }

}

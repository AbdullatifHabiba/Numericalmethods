import { Component } from '@angular/core';
import{HttpClient}from'@angular/common/http';

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
  precision:number=0;
 //////////////////////////////////////////////////////////
  constructor(private http:HttpClient){}
  GauessEliminationEq(equations:string[])
  {
    this.http.get('http://localhost:8080/method/GaussElimination',{
      params:{
        Matrix:equations
      },
      observe:'response'
  }).subscribe(response=>{
    this.result=response.body;
   })
  }
  GauessJordanEq(equations:string[])
  {
    this.http.get('http://localhost:8080/method/GaussJordan',{
      params:{
        Matrix:equations
      },
      observe:'response'
  }).subscribe(response=>{
    this.result=response.body;
   })
  }
  LU_Eq(equations:string[])
  {
    this.http.get('http://localhost:8080/method/LUDecomposition',{
      params:{
        Matrix:equations
      },
      observe:'response'
  }).subscribe(response=>{
    this.result=response.body;
   })
  }
  jacobiEq(equations:string[],numbOfIter:number,intial:number[],relativeErr:number)
  {
    this.http.get('http://localhost:8080/method/Jacobi',{
      params:{
        Matrix:equations,
        Intial:intial,
        numbOfIter:numbOfIter,
        relativeErr:relativeErr
      },
      observe:'response'
  }).subscribe(response=>{
    this.result=response.body;
   })
  }
  sedeil_Eq(equations:string[],numbOfIter:number,intial:number[],relativeErr:number)
  {
    this.http.get('http://localhost:8080/method/Seidel',{
      params:{
        Matrix:equations,
        Intial:intial,
        numbOfIter:numbOfIter,
        relativeErr:relativeErr
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
    this.ArrOfEquations=this.equations.split(",");
    this.initial=this.initialGuess.split(",");

    if(this.Gauess)
    {
      this. GauessEliminationEq(this.ArrOfEquations);
    }
    else if(this.Jordan)
    {
      this.GauessJordanEq(this.ArrOfEquations);
    }
    else if(this.LU_decomposition)
    {
       this.LU_Eq(this.ArrOfEquations);
    }
    else if(this.Jacobi)
    {
      this. jacobiEq(this.ArrOfEquations,this.numberOfIteration,this.initial,this.RelativeError);
    }
    else{
         this.sedeil_Eq(this.ArrOfEquations,this.numberOfIteration,this.initial,this.RelativeError);
    }
  }
  
  dropList1()
  {
    this.setToggle(true,false,false,false,false,false);
    this.toggle1=true;
    this.drop=false;
    this.NonDrop=true;
    this.Gauess=true;
  }

  dropList2(){
    this.setToggle(false,true,false,false,false,false);
    this.NonDrop=false;
    this.drop=false;
    this.NonDrop=true;
    this.Jordan=true;
  }
  dropList3()
  {
    this.setToggle(false,false,true,false,false,false);
    this.drop=false;
    this.NonDrop=true;
    this.LU_decomposition=true;
  }
  dropList4()
  {
    this.setToggle(false,false,false,true,false,false);
    this.drop=true;
    this.NonDrop=true;
    this.sediel=true;
  }
  dropList5()
  {
    this.setToggle(false,false,false,false,true,false);
    this.drop=true;
    this.NonDrop=true;
    this.Jacobi=true;
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

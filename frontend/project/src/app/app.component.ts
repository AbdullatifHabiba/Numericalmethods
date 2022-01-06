import { Component } from '@angular/core';
import{HttpClient}from'@angular/common/http';
import { Point } from './Point';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'project';

  RecipeImagePath:any="assets/images/download.jpg";
  equations:string="";
  drop:any;
  NonDrop:any;
  ArrOfEquations:any=[];
  initial:any=[];

  color="red";color2="black";colorf="green"
  points:Array<Point>=[]
/////////////////////////////////////////////
initialX:number=0;
secondX:number=0;
openEquation:string=""
Eps:number=0
/////////////////////////////////////////////
  initialGuess:string="";
  numberOfIteration:number=0;
  RelativeError:number=0;
  result:any;
  rootx:any;
  rooty:any;

  Gauess:boolean=false;
  Jordan:boolean=false;
  Jacobi:boolean=false;
  sediel:boolean=false;
  LU_decomposition:boolean=false;

  openCheck:boolean=true;
  secant:boolean=false;
  ///////////////////////////////////////
  precisionOfResult:number=0;

  typeOfLU:string="";
  typeOfOpenmethod:string="";

  constructor(private http:HttpClient){}
  GauessEliminationEq(equations:string[],precision:number)
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
  GauessJordanEq(equations:string[],precision:number)
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
  LUDecomposition(equations:string[],Type:string,precision:number)
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


  jacobiEq(equations:string[],numbOfIter:number,intial:number[],relativeErr:number,precision:number)
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
  sedeil_Eq(equations:string[],numbOfIter:number,intial:number[],relativeErr:number,precision:number)
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
/////////////////////////////////////////////////////////////////////openmethod
fixed(equation:string,init:number,iter:number,ep:number,precision:number)
  {
    this.http.get('http://localhost:8080/method/fixed',{
      params:{
        equation:equation,
        initial:init,
        iterations:iter,
        eps:ep,
        precision:precision
      },
      observe:'response'
  }).subscribe(response=>{
    this.result=response.body;
   })
  }
  newton(equation:string,init:number,iter:number,ep:number,precision:number)
  {
    this.http.get('http://localhost:8080/method/newton',{
      params:{
        equation:equation,
        initial:init,
        iterations:iter,
        eps:ep,
        precision:precision
      },
      observe:'response'
  }).subscribe(response=>{
    this.result=response.body;
    this.points=this.result;
    this.rootx=this.result[0].rootx;
    this.rooty=this.result[0].rooty;

    this.points.splice(0,1)
    console.log(this.points)
   })
  }
//////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////
toggle1 = false;
toggle2=false;
toggle3=false;
toggle4=false;
toggle5=false;
toggle6=false;
toggle7=false;

  submitForm(){
   this.toggle6=true;
    this.ArrOfEquations=this.equations.split(",");
    this.initial=this.initialGuess.split(",");
    let equat=this.openEquation.replace("+","p");
    //open method calling
    if(this.openCheck)
    {
        if(this.typeOfOpenmethod=="Bisection")
        {

        }else if(this.typeOfOpenmethod=="False-Position")
        {

        }else if(this.typeOfOpenmethod=="Fixed_point")
        {
            console.log("ok"+this.openEquation+"uu"+this.Eps+" "+this.numberOfIteration);
            this.fixed(equat,this.initialX,this.numberOfIteration,this.Eps,this.precisionOfResult);
        }else if(this.typeOfOpenmethod=="Newton-Raphson"){
          console.log("ok"+this.openEquation+"uu"+this.Eps+" "+this.numberOfIteration);
            this.newton(equat,this.initialX,this.numberOfIteration,this.Eps,this.precisionOfResult);

        }else{

        }
    }
    //natural method calling
    else{
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
  }

  dropList1()
  {
    this.setToggle(true,false,false,false,false,false,false);
    this.setMethods(true,false,false,false,false,false);
    this.toggle1=true;
    this.drop=false;
    this.NonDrop=true;
    this.result="";
  }

  dropList2(){
    this.setToggle(false,true,false,false,false,false,false);
    this.setMethods(false,true,false,false,false,false);
    this.NonDrop=false;
    this.drop=false;
    this.NonDrop=true;
    this.result="";
  }
  dropList3()
  {
    this.setToggle(false,false,true,false,false,false,false);
    this.setMethods(false,false,true,false,false,false);
    this.drop=false;
    this.NonDrop=true;
    this.result="";
  }
  dropList4()
  {
    this.setToggle(false,false,false,true,false,false,false);
    this.setMethods(false,false,false,true,false,false);
    this.drop=true;
    this.NonDrop=true;
    this.result="";
  }
  dropList5()
  {
    this.setToggle(false,false,false,false,true,false,false);
    this.setMethods(false,false,false,false,true,false);
    this.drop=true;
    this.NonDrop=true;
    this.result="";
  }
  dropList6()
  {
    this.toggle6=false;
  }
  dropList7(){
    console.log(this.openCheck);
    this.setToggle(false,false,false,false,false,false,true);
    this.setMethods(false,false,false,false,false,true);
    this.drop=false;
    this.NonDrop=false;
    this.openCheck=true;
    this.result="";
    if(this.typeOfOpenmethod=="Secant")
       {
         this.secant=true;
       }
  }
  setToggle(t1:boolean,t2:boolean,t3:boolean,t4:boolean,t5:boolean,t6:boolean,t7:boolean){
    this.toggle1=t1;
    this.toggle2=t2;
    this.toggle3=t3;
    this.toggle4=t4;
    this.toggle5=t5;
    this.toggle6=t6;
    this.toggle7=t7;
  }
  setMethods(Gauess:boolean,Jordan:boolean,LU_decomposition:boolean,jacob:boolean,seid:boolean,open:boolean)
  {
    this.Gauess=Gauess;
    this.Jordan=Jordan;
    this.sediel=seid;
    this.Jacobi=jacob;
    this.LU_decomposition=LU_decomposition;
    this.openCheck=open;
  }



}

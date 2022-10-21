package com.example.cookingapp

class ReciepModel(
    private var name:String,
    private var text:String,
    private var time: Int,
    private var ppl: Int) {

    fun getName():String{
        return name
    }
    fun setName(name: String){
        this.name = name
    }

    fun getText():String{
        return text
    }

    fun setText(text: String){
        this.text = text
    }

    fun getTime():Int{
       return time
    }

    fun setTime(time: Int){
       this.time = time
    }

    fun getPpl():Int{
       return ppl
    }

    fun setPpl(ppl: Int){
       this.ppl = ppl
    }

}
package com.example.cookingapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel


class CookNowViewModel : ViewModel() {

    private var _stepIndex = MutableLiveData(0)
    private val stepIndex get() = _stepIndex.value!!

    val currentStep: LiveData<CookStep> = Transformations.map(_stepIndex) {
        val currentStepDataModel = dish.steps[it]
        CookStep(
            stepNumber = it + 1,
            description = currentStepDataModel.description,
            numberOfSteps = dish.steps.size,
        )
    }

    val dish = Dish(
        "Beshbarmak",
        steps = listOf(
            CookStepDataModel(
                "Chop pumpkin and sweet potato into small chunks, put them to the bowl.\nThen add water and paprika. Select program no. 3 and cook for 20 mins."),
            CookStepDataModel(
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa."),
            CookStepDataModel(
                "Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu."),
            CookStepDataModel(
                "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium."),
            CookStepDataModel(
                "Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim."),
            CookStepDataModel(
                "Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. "),
            CookStepDataModel(
                "Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. "),
            CookStepDataModel(
                "Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum."),
            CookStepDataModel(
                "Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. "),
            CookStepDataModel(
                "Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo."),
        )
    )

    fun onClickDecrease() {
        if (stepIndex in 1..9) {
            _stepIndex.value = stepIndex - 1
        }
    }

    fun onClickIncrease() {
        if (stepIndex in 0 until 9) {
            _stepIndex.value = stepIndex + 1
        }
    }
}



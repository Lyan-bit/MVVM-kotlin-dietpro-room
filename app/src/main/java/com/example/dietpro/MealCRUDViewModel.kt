package com.example.dietpro

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class MealCRUDViewModel constructor(context: Context): ViewModel() {


    companion object {
        private val repository by lazy { Repository() }
        private var instance: MealCRUDViewModel? = null
        fun getInstance(context: Context): MealCRUDViewModel {
            return instance ?: MealCRUDViewModel(context)
        }
    }

    val allMeals: LiveData<List<MealEntity>> = repository.allMeals.asLiveData()

    val allMealMealIds: LiveData<List<String>> = repository.allMealmealIds.asLiveData()
    val allMealMealNames: LiveData<List<String>> = repository.allMealmealNames.asLiveData()
    val allMealCaloriess: LiveData<List<Double>> = repository.allMealcaloriess.asLiveData()
    val allMealDatess: LiveData<List<String>> = repository.allMealdatess.asLiveData()
    val allMealImagess: LiveData<List<String>> = repository.allMealimagess.asLiveData()
    val allMealAnalysiss: LiveData<List<String>> = repository.allMealanalysiss.asLiveData()
    val allMealUserNames: LiveData<List<String>> = repository.allMealuserNames.asLiveData()
    private var currentMeal: MealEntity? = null
    private var currentMeals: List<MealEntity> = ArrayList()

    fun searchByMealmealId(searchQuery: String): LiveData<List<MealEntity>>  {
        return repository.searchByMealmealId(searchQuery).asLiveData()
    }

    fun searchByMealmealName(searchQuery: String): LiveData<List<MealEntity>>  {
        return repository.searchByMealmealName(searchQuery).asLiveData()
    }

    fun searchByMealcalories(searchQuery: Double): LiveData<List<MealEntity>>  {
        return repository.searchByMealcalories(searchQuery).asLiveData()
    }

    fun searchByMealdates(searchQuery: String): LiveData<List<MealEntity>>  {
        return repository.searchByMealdates(searchQuery).asLiveData()
    }

    fun searchByMealimages(searchQuery: String): LiveData<List<MealEntity>>  {
        return repository.searchByMealimages(searchQuery).asLiveData()
    }

    fun searchByMealanalysis(searchQuery: String): LiveData<List<MealEntity>>  {
        return repository.searchByMealanalysis(searchQuery).asLiveData()
    }

    fun searchByMealuserName(searchQuery: String): LiveData<List<MealEntity>>  {
        return repository.searchByMealuserName(searchQuery).asLiveData()
    }


	fun getMealByPK(value: String): Flow<Meal> {
        val res: Flow<List<MealEntity>> = repository.searchByMealmealId(value)
        return res.map { meal ->
            val itemx = Meal.createByPKMeal(value)
            if (meal.isNotEmpty()) {
            itemx.mealId = meal[0].mealId
            }
            if (meal.isNotEmpty()) {
            itemx.mealName = meal[0].mealName
            }
            if (meal.isNotEmpty()) {
            itemx.calories = meal[0].calories
            }
            if (meal.isNotEmpty()) {
            itemx.dates = meal[0].dates
            }
            if (meal.isNotEmpty()) {
            itemx.images = meal[0].images
            }
            if (meal.isNotEmpty()) {
            itemx.analysis = meal[0].analysis
            }
            if (meal.isNotEmpty()) {
            itemx.userName = meal[0].userName
            }
            itemx
        }
    }

	  suspend fun createMeal(x: MealEntity) {
	    repository.createMeal(x)
	    currentMeal = x
	}

    suspend fun editMeal(x: MealEntity) {
		 repository.updateMeal(x)
         currentMeal = x
    }

   fun setSelectedMeal(x: MealEntity) {
		 currentMeal = x
	}

    suspend fun deleteMeal(id: String) {
  		  repository.deleteMeal(id)
  		  currentMeal = null
  	}

      suspend fun searchMealByDate(dates: String) : ArrayList<Meal> {
				currentMeals = repository.searchByMealdates2(dates)
				var itemsList = ArrayList<Meal>()
				for (x in currentMeals.indices) {
					val vo: MealEntity = currentMeals[x]
				    val itemx = Meal.createByPKMeal(vo.mealId)
					    itemx.mealId = vo.mealId
					    itemx.mealName = vo.mealName
					    itemx.calories = vo.calories
					    itemx.dates = vo.dates
					    itemx.images = vo.images
					    itemx.analysis = vo.analysis
					    itemx.userName = vo.userName
				itemsList.add(itemx)
			}
		return itemsList
		}

    suspend fun addUsereatsMeal(userName: String, mealId: String) {
        repository.addUsereatsMeal(userName,mealId)
    }

    suspend fun removeUsereatsMeal(userName: String, mealId: String) {
        repository.removeUsereatsMeal("NULL", mealId)
    }

        suspend fun listMeal(): List<MealEntity> {
	        currentMeals = repository.listMeal()
	        return currentMeals
	    }

	suspend fun listAllMeal(): ArrayList<Meal> {
		currentMeals = repository.listMeal()
		var res = ArrayList<Meal>()
			for (x in currentMeals.indices) {
					val vo: MealEntity = currentMeals[x]
				    val itemx = Meal.createByPKMeal(vo.mealId)
	            itemx.mealId = vo.mealId
            itemx.mealName = vo.mealName
            itemx.calories = vo.calories
            itemx.dates = vo.dates
            itemx.images = vo.images
            itemx.analysis = vo.analysis
            itemx.userName = vo.userName
			res.add(itemx)
		}
		return res
	}

    suspend fun stringListMeal(): List<String> {
        currentMeals = repository.listMeal()
        val res: ArrayList<String> = ArrayList()
        for (x in currentMeals.indices) {
            res.add(currentMeals[x].toString())
        }
        return res
    }

    suspend fun getMealByPK2(value: String): Meal? {
        val res: List<MealEntity> = repository.searchByMealmealId2(value)
	        return if (res.isEmpty()) {
	            null
	        } else {
	            val vo: MealEntity = res[0]
	            val itemx = Meal.createByPKMeal(value)
	            itemx.mealId = vo.mealId
            itemx.mealName = vo.mealName
            itemx.calories = vo.calories
            itemx.dates = vo.dates
            itemx.images = vo.images
            itemx.analysis = vo.analysis
            itemx.userName = vo.userName
	            itemx
	        }
    }

    suspend fun retrieveMeal(value: String): Meal? {
            return getMealByPK2(value)
    }

    suspend fun allMealMealIds(): ArrayList<String> {
        currentMeals = repository.listMeal()
        val res: ArrayList<String> = ArrayList()
            for (meal in currentMeals.indices) {
                res.add(currentMeals[meal].mealId)
            }
        return res
    }

    fun setSelectedMeal(i: Int) {
        if (i < currentMeals.size) {
            currentMeal = currentMeals[i]
        }
    }

    fun getSelectedMeal(): MealEntity? {
        return currentMeal
    }

    suspend fun persistMeal(x: Meal) {
        val vo = MealEntity(x.mealId, x.mealName, x.calories, x.dates, x.images, x.analysis, x.userName)
        repository.updateMeal(vo)
        currentMeal = vo
    }

    suspend fun searchByMealmealId2(mealIdx: String): List<MealEntity> {
        currentMeals = repository.searchByMealmealId2(mealIdx)
	    return currentMeals
	}
    suspend fun searchByMealmealName2(mealNamex: String): List<MealEntity> {
        currentMeals = repository.searchByMealmealName2(mealNamex)
	    return currentMeals
	}
    suspend fun searchByMealcalories2(caloriesx: Double): List<MealEntity> {
        currentMeals = repository.searchByMealcalories2(caloriesx)
	    return currentMeals
	}
    suspend fun searchByMealdates2(datesx: String): List<MealEntity> {
        currentMeals = repository.searchByMealdates2(datesx)
	    return currentMeals
	}
    suspend fun searchByMealimages2(imagesx: String): List<MealEntity> {
        currentMeals = repository.searchByMealimages2(imagesx)
	    return currentMeals
	}
    suspend fun searchByMealanalysis2(analysisx: String): List<MealEntity> {
        currentMeals = repository.searchByMealanalysis2(analysisx)
	    return currentMeals
	}
    suspend fun searchByMealuserName2(userNamex: String): List<MealEntity> {
        currentMeals = repository.searchByMealuserName2(userNamex)
	    return currentMeals
	}


}
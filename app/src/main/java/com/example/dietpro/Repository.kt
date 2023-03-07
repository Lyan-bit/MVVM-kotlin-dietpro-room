package com.example.dietpro

import kotlinx.coroutines.flow.Flow

class Repository : MealRepository  {

    private val mealDAO: MealEntityDAO by lazy { DietproApplication.database.mealDao() }

    val allMeals: Flow<List<MealEntity>> = mealDAO.listMeals()

    val allMealmealIds: Flow<List<String>> = mealDAO.listMealmealIds()
    val allMealmealNames: Flow<List<String>> = mealDAO.listMealmealNames()
    val allMealcaloriess: Flow<List<Double>> = mealDAO.listMealcaloriess()
    val allMealdatess: Flow<List<String>> = mealDAO.listMealdatess()
    val allMealimagess: Flow<List<String>> = mealDAO.listMealimagess()
    val allMealanalysiss: Flow<List<String>> = mealDAO.listMealanalysiss()
    val allMealuserNames: Flow<List<String>> = mealDAO.listMealuserNames()

    //Create
    override suspend fun createMeal(meal: MealEntity) {
        mealDAO.createMeal(meal)
    }

    //Read
    override suspend fun listMeal(): List<MealEntity> {
        return mealDAO.listMeal()
    }

    //Update
    override suspend fun updateMeal(meal: MealEntity) {
        mealDAO.updateMeal(meal)
    }

    //Delete all Meals
    override suspend fun deleteMeals() {
       mealDAO.deleteMeals()
    }

    //Delete a Meal
	override suspend fun deleteMeal(mealId: String) {
	   mealDAO.deleteMeal(mealId)
    }
    
     //Search with live data
     override fun searchByMealmealId (searchQuery: String): Flow<List<MealEntity>>  {
         return mealDAO.searchByMealmealId(searchQuery)
     }
     
     //Search with live data
     override fun searchByMealmealName (searchQuery: String): Flow<List<MealEntity>>  {
         return mealDAO.searchByMealmealName(searchQuery)
     }
     
     //Search with live data
     override fun searchByMealcalories (searchQuery: Double): Flow<List<MealEntity>>  {
         return mealDAO.searchByMealcalories(searchQuery)
     }
     
     //Search with live data
     override fun searchByMealdates (searchQuery: String): Flow<List<MealEntity>>  {
         return mealDAO.searchByMealdates(searchQuery)
     }
     
     //Search with live data
     override fun searchByMealimages (searchQuery: String): Flow<List<MealEntity>>  {
         return mealDAO.searchByMealimages(searchQuery)
     }
     
     //Search with live data
     override fun searchByMealanalysis (searchQuery: String): Flow<List<MealEntity>>  {
         return mealDAO.searchByMealanalysis(searchQuery)
     }
     
     //Search with live data
     override fun searchByMealuserName (searchQuery: String): Flow<List<MealEntity>>  {
         return mealDAO.searchByMealuserName(searchQuery)
     }
     

    //Search with suspend
     override suspend fun searchByMealmealId2 (mealId: String): List<MealEntity> {
          return mealDAO.searchByMealmealId2(mealId)
     }
	     
    //Search with suspend
     override suspend fun searchByMealmealName2 (mealName: String): List<MealEntity> {
          return mealDAO.searchByMealmealName2(mealName)
     }
	     
    //Search with suspend
     override suspend fun searchByMealcalories2 (calories: Double): List<MealEntity> {
          return mealDAO.searchByMealcalories2(calories)
     }
	     
    //Search with suspend
     override suspend fun searchByMealdates2 (dates: String): List<MealEntity> {
          return mealDAO.searchByMealdates2(dates)
     }
	     
    //Search with suspend
     override suspend fun searchByMealimages2 (images: String): List<MealEntity> {
          return mealDAO.searchByMealimages2(images)
     }
	     
    //Search with suspend
     override suspend fun searchByMealanalysis2 (analysis: String): List<MealEntity> {
          return mealDAO.searchByMealanalysis2(analysis)
     }
	     
    //Search with suspend
     override suspend fun searchByMealuserName2 (userName: String): List<MealEntity> {
          return mealDAO.searchByMealuserName2(userName)
     }
	     


	//Add remove Relationship
     override suspend fun addUsereatsMeal(userName: String, mealId: String) {
          return  mealDAO.addUsereatsMeal(userName, mealId)
     }
        
     override suspend fun removeUsereatsMeal(userName: String, mealId: String) {
          return  mealDAO.removeUsereatsMeal(userName, mealId)
     }
        
}

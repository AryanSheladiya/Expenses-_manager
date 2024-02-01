var dbName ="Category"

data class CategoryModel(var id : Int,var category: String)

data class IncomeExpenses(
    var id: Int,
    var incomeExpenses: String,
    var amount: Int,
    var category: String,
    var date: String,
    var mode:String,
    var note: String)
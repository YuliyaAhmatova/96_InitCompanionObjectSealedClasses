import kotlinx.coroutines.delay

suspend fun main() {
    println("Выберите кофе: 1 - Американо, 2 - Капучино, 3 - Латте")
    val nameInput = readln()

    println("Количество сахара: 1, 2, 3")
    val sugarInput = readln()

    println("Объем кофе: 0.2, 0.3, 0.4")
    val volumeInput = readln()

    var milkInput: String? = null
    if (nameInput=="2"){
        println("Количество молока: 1, 2, 3")
        milkInput = readln()
    }

    selectMenu(selectedMenu(nameInput,sugarInput,volumeInput, milkInput))
}

sealed class Coffee(val name:String, val sugar:String, val volume:String){
    class Americano(sugar: String, volume: String) :Coffee("Американо", sugar, volume)
    class Cappuccino(sugar: String, volume: String, val milk:String) :Coffee("Капучино", sugar, volume)
    class Latte(sugar: String, volume: String) :Coffee("Латте", sugar, volume)

}

fun selectedMenu(nameTwo: String, sugarTwo: String, volumeTwo: String, milkTwo:String?): Coffee? {
    return when(nameTwo){
        "1" -> Coffee.Americano(sugarTwo,volumeTwo)
        "2" -> Coffee.Cappuccino(sugarTwo,volumeTwo, milkTwo!!)
        "3" -> Coffee.Latte(sugarTwo,volumeTwo)
        else -> {
            println("Невеное значение")
            null
        }
    }
}

suspend fun selectMenu(menu:Coffee?){
    println("Кофе готовиться...")
    for (loading in 0..100 step 10) {
        println("$loading%")
        delay(500L)
    }
    when (menu){
        is Coffee.Americano -> println("Ваш кофе готов: ${menu.name}, сахар: ${menu.sugar}, объем: ${menu.volume}")
        is Coffee.Cappuccino -> println("Ваш кофе готов: ${menu.name}, сахар: ${menu.sugar}, объем: ${menu.volume}, молоко: ${menu.milk}")
        is Coffee.Latte -> println("Ваш кофе готов: ${menu.name}, сахар: ${menu.sugar}, объем: ${menu.volume}")
        else -> {
            println("Ввод неверного значения")
        }
    }
}
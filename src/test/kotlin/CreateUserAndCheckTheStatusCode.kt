import com.google.gson.JsonParser
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import khttp.post
import khttp.get
import java.util.*

class CreateUserAndCheckTheStatusCode : StringSpec() {

    private val createUserUrl =
        "https://petstore.swagger.io/v2/user" // url для отправки запроса на получение данных о юзере

    val userNameToCheck = UUID.randomUUID().toString() // Генерация случайного имени
    private var createdUserId: Long = 0 // сюда будет записываться id созданного юзера для сравнения
    val data = mapOf(
        "username" to "$userNameToCheck",
        "firstName" to "Kotlin",
        "lastName" to "Kotlinov",
        "email" to "kotlin@yandex.ru",
        "password" to "kotlin",
        "phone" to "89999999999"
    )

    init {
        "it is possible to create user" {
            val createUserResponse = post(createUserUrl, json = data)
            createUserResponse.statusCode shouldBe 200

            createdUserId = createUserResponse.jsonObject.getLong("message")
        }

        "check created user in get request" {
            val getUserResponse = get("$createUserUrl/$userNameToCheck")
            getUserResponse.statusCode shouldBe 200

            getUserResponse.jsonObject.getString("username") shouldBe userNameToCheck
            getUserResponse.jsonObject.getLong("id") shouldBe createdUserId


        }
    }
}
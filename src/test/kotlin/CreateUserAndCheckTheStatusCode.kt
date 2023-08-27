import com.google.gson.JsonParser
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import khttp.post
import khttp.get

class CreateUserAndCheckTheStatusCode : StringSpec() {
    private val createUserUrl = "https://petstore.swagger.io/v2/user"
    private var createdUserId: Long = 0

    init {
        "it is possible to create user" {
            val data = mapOf(
                "username" to "KotlinTestUser",
                "firstName" to "Kotlin",
                "lastName" to "Kotlinov",
                "email" to "kotlin@yandex.ru",
                "password" to "kotlin",
                "phone" to "89999999999"
            )
            val createUserResponse = post(createUserUrl, json = data)
            createUserResponse.statusCode shouldBe 200

            createdUserId = createUserResponse.jsonObject.getLong("message")
        }

        "check created user in get request" {
            //val getUserResponse = get("$createUserUrl/$createdUserId")
            val getUserResponse = get("https://petstore.swagger.io/v2/user/KotlinTestUser")
            getUserResponse.statusCode shouldBe 200
            "user exists and username is correct" {
                getUserResponse.jsonObject.getString("username") shouldBe "KotlinTestUser"
            }
        }
    }
}
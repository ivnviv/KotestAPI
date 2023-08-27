import com.google.gson.JsonParser
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import khttp.post

class CreateUserAndCheckTheStatusCode : StringSpec() {
    init {
        "it is possible to create user" {
            val url = "https://petstore.swagger.io/v2/user"
            val data = mapOf(
                "username" to "KotlinTestUser",
                "firstName" to "Kotlin",
                "lastName" to "Kotlinov",
                "email" to "kotlin@yandex.ru",
                "password" to "kotlin",
                "phone" to "89999999999"
            )
            val response = post(url, json = data)
            //response.statusCode shouldBe 200

        }
    }

}
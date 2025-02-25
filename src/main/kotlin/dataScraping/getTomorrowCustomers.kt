package org.example.dataScraping

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

const val TOMORROW_BASE_URL = "https://www.tomorrow.one/de-DE/"


fun getTomorrowCustomerNumber() : Int {

    val driver: WebDriver = ChromeDriver()

    try {
        driver.get(TOMORROW_BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))

        val cookieRejectButtonCSSSelector = "#__next > dialog > form > button:nth-child(2) > span"
        val rejectButton: WebElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cookieRejectButtonCSSSelector))
        )
        rejectButton.click()

        val customerNumberCSSSelector = """
                #content > div:nth-child(3) > div > div > div > div > 
                div:nth-child(1) > div > div.sc-bf35565f-1.gLdIXT
            """.trimIndent()
        val numberElement: WebElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector(customerNumberCSSSelector))
        )
        val customerNumber = numberElement.text
        val customerNumberCleaned = customerNumber.replace(oldValue = ".", newValue = "")
        println("Current Tomorrow customer number: $customerNumber")
        return customerNumberCleaned.toInt()
    } catch (e: Exception) {
        throw e
    } finally {
        driver.quit()
    }
}

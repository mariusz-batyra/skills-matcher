package pl.mbatyra.skillsmatcher

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import pl.mbatyra.skillsmatcher.adapter.ui.MainWindow
import java.awt.EventQueue


@SpringBootApplication
class SkillsMatcherApplication(
    private val mainWindow: MainWindow
) : CommandLineRunner {

    override fun run(vararg args: String) {
        EventQueue.invokeLater {
            mainWindow.initialize()
        }
    }
}

fun main(args: Array<String>) {
    SpringApplicationBuilder(SkillsMatcherApplication::class.java)
        .headless(false)
        .run(*args)
}
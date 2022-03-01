package pl.mbatyra.skillsmatcher.adapter.ui

import org.springframework.stereotype.Component
import pl.mbatyra.skillsmatcher.domain.SkillsMatcher
import java.awt.BorderLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

@Component
class MainWindow(
    private val skillsMatcher: SkillsMatcher
) : JFrame() {

    val searchTextField = JTextField()
    val searchButton = JButton()
    val resultLabel = JLabel()
    val newSkillButton = JButton()
    val categoriesCombobox = JComboBox<String>()

    fun initialize() {
        title = "Skills matcher app"
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(300, 200)
        val panel = JPanel(BorderLayout())
        val searchResultPanel = JPanel(BorderLayout())
        addSearchTextArea(panel)
        addSearchButton(searchResultPanel)
        addResultLabel(searchResultPanel)
        addNewSkillSection(panel)
        panel.add(searchResultPanel, BorderLayout.AFTER_LAST_LINE)

        contentPane = panel
        isVisible = true
    }

    private fun addSearchTextArea(panel: JPanel) {
        searchTextField.text = ""
        panel.add(searchTextField, BorderLayout.PAGE_START)
        searchTextField.addKeyListener(object : KeyAdapter() {
            override fun keyPressed(keyEvent: KeyEvent) {
                if (keyEvent.keyCode == KeyEvent.VK_ENTER) {
                    tryMatchSkill()
                }
            }
        })
    }

    private fun addResultLabel(panel: JPanel) {
        resultLabel.isVisible = true
        panel.add(resultLabel, BorderLayout.CENTER)
    }

    private fun addSearchButton(panel: JPanel) {
        searchButton.text = "Search"
        panel.add(searchButton, BorderLayout.AFTER_LAST_LINE)
        searchButton.addActionListener {
            tryMatchSkill()
        }
    }

    private fun tryMatchSkill() {
        val match = skillsMatcher.match(searchTextField.text)
        if (match == null) {
            println("skill cannot be match")
            resultLabel.text = "not found"
            categoriesCombobox.isVisible = true
            newSkillButton.isVisible = true
        } else {
            println("skill match: $match")
            resultLabel.text = match
        }
    }

    private fun addNewSkillSection(panel: JPanel) {
        skillsMatcher.getDefinedCategories().forEach { categoriesCombobox.addItem(it) }
        categoriesCombobox.isVisible = false
        panel.add(categoriesCombobox, BorderLayout.CENTER)

        newSkillButton.text = "Add new skill"
        newSkillButton.isVisible = false
        panel.add(newSkillButton, BorderLayout.WEST)
        newSkillButton.addActionListener {
            skillsMatcher.addNewSkill(categoriesCombobox.selectedItem as String, searchTextField.text)

            categoriesCombobox.removeAllItems()
            skillsMatcher.getDefinedCategories().forEach { categoriesCombobox.addItem(it) }
            categoriesCombobox.isVisible = false
            newSkillButton.isVisible = false
            tryMatchSkill()
            panel.repaint()
        }
    }
}
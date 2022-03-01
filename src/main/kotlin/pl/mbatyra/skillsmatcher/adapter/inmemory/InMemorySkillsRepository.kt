package pl.mbatyra.skillsmatcher.adapter.inmemory

import org.springframework.stereotype.Component
import pl.mbatyra.skillsmatcher.domain.SkillsRepository

@Component
class InMemorySkillsRepository : SkillsRepository {

    private val skills = mutableMapOf(
        "framework" to listOf("spring", "jee"),
        "language" to listOf("java", "kotlin"),
        "servers" to listOf("tomcat")
    )

    override fun find(skillName: String): String? =
        skills.entries
            .find { it.value.contains(skillName.lowercase()) }
            ?.key

    override fun findAllCategories(): Set<String> = skills.keys

    override fun addNewSkill(categoryName: String, skillName: String) {
        skills[categoryName] = listOf(skillName)
    }
}
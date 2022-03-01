package pl.mbatyra.skillsmatcher.domain

import org.springframework.stereotype.Component

@Component
class SkillsMatcher(
    private val skillsRepository: SkillsRepository
) {
    fun match(skillName: String): String? = skillsRepository.find(skillName)
    fun getDefinedCategories(): Set<String> = skillsRepository.findAllCategories()
    fun addNewSkill(categoryName: String, skillName: String) = skillsRepository.addNewSkill(categoryName, skillName)
}
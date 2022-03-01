package pl.mbatyra.skillsmatcher.domain

interface SkillsRepository {
    fun find(skillName: String): String?
    fun findAllCategories(): Set<String>
    fun addNewSkill(categoryName: String, skillName: String)
}
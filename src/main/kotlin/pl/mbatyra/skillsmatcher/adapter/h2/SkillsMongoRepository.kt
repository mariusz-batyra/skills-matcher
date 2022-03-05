package pl.mbatyra.skillsmatcher.adapter.h2

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.mbatyra.skillsmatcher.domain.SkillsRepository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Repository
class SkillsMongoRepository(
    private val skillsJpaRepository: SkillsJpaRepository
) : SkillsRepository {

    override fun find(skillName: String): String? =
        skillsJpaRepository.findAll()
            .firstOrNull { it.skillName.lowercase() == skillName.lowercase() }
            ?.categoryName

    override fun findAllCategories(): Set<String> = setOf(
        "LANGUAGE", "FRAMEWORK", "DATABASE", "SERVER", "OTHER"
    )

    override fun addNewSkill(categoryName: String, skillName: String) {
        skillsJpaRepository.save(SkillEntity(categoryName = categoryName, skillName = skillName.lowercase()))
    }
}

@Repository
interface SkillsJpaRepository : CrudRepository<SkillEntity, Long>

@Entity
class SkillEntity(
    @Id
    @GeneratedValue
    var id: Long? = null,
    var categoryName: String,
    var skillName: String
)
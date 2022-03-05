package pl.mbatyra.skillsmatcher.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.mbatyra.skillsmatcher.SkillsMatcherApplication
import pl.mbatyra.skillsmatcher.adapter.inmemory.InMemorySkillsRepository

@SpringBootTest(
    classes = [SkillsMatcherApplication::class],
    properties = ["application.environment=test"]
)
class SkillsRepositoryIT {
    @Autowired
    lateinit var skillsRepository: InMemorySkillsRepository

    @Test
    fun `should add new skill`() {
        // when
        skillsRepository.addNewSkill("category", "skill")

        //then
        Assertions.assertEquals("category", skillsRepository.find("skill"))
    }
}
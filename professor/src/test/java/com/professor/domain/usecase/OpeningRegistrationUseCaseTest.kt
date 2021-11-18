package com.professor.domain.usecase

import com.data.repository.ProfessorRepositoryImpl
import com.domain.usecase.OpeningRegistrationUseCase
import com.professor.BaseTest
import io.mockk.mockk
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

internal class OpeningRegistrationUseCaseTest : BaseTest() {

    private lateinit var useCase: OpeningRegistrationUseCase
    private lateinit var repository: ProfessorRepositoryImpl

    @Before
    fun setUp() {
        repository = mockk()
        useCase = OpeningRegistrationUseCase(repository)
    }

    @Test
    fun `validateOpeningRegistrationData should succeed when parsing valid data`() {
        val validation = useCase.validateOpeningRegistrationData(
            TITLE,
            DESCRIPTION,
            ACTIVITIES,
            PREREQUISITES,
            EMAIL
        )

        assertTrue(validation)
    }

    @Test
    fun `validateOpeningRegistrationData should fail when parsing an invalid data`() {
        val validation = useCase.validateOpeningRegistrationData(
            TITLE,
            "    ",
            ACTIVITIES,
            "",
            EMAIL
        )

        assertFalse(validation)
    }
}
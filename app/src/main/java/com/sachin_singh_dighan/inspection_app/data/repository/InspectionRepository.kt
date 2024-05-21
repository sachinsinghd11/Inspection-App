package com.sachin_singh_dighan.inspection_app.data.repository

import com.sachin_singh_dighan.inspection_app.data.api.NetworkService
import com.sachin_singh_dighan.inspection_app.data.local.dao.InspectionDao
import com.sachin_singh_dighan.inspection_app.data.local.entity.AnswerChoiceEntity
import com.sachin_singh_dighan.inspection_app.data.local.entity.AreaEntity
import com.sachin_singh_dighan.inspection_app.data.local.entity.CategoryEntity
import com.sachin_singh_dighan.inspection_app.data.local.entity.InspectionEntity
import com.sachin_singh_dighan.inspection_app.data.local.entity.InspectionTypeEntity
import com.sachin_singh_dighan.inspection_app.data.local.entity.QuestionEntity
import com.sachin_singh_dighan.inspection_app.data.local.entity.SurveyEntity
import com.sachin_singh_dighan.inspection_app.data.model.AnswerChoice
import com.sachin_singh_dighan.inspection_app.data.model.Area
import com.sachin_singh_dighan.inspection_app.data.model.Category
import com.sachin_singh_dighan.inspection_app.data.model.Inspection
import com.sachin_singh_dighan.inspection_app.data.model.InspectionType
import com.sachin_singh_dighan.inspection_app.data.model.Question
import com.sachin_singh_dighan.inspection_app.data.model.Survey
import io.realm.kotlin.ext.realmListOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InspectionRepository @Inject constructor(
    private val networkService: NetworkService,
    private val inspectionDao: InspectionDao
) {
    fun fetchInspections(): Flow<InspectionEntity> {
        return flow {

            emit(networkService.startInspection())
            //emit(AppConstant.inspectionStartList)

        }.distinctUntilChanged().map {
            inspectionDao.deleteAll()
            inspectionDao.update(it)
            inspectionDao.findAll()

            //it
        }.flatMapLatest {
            flow { it[0] }
        }
    }

    private fun <T> listOf(element: Unit?): List<T> {
        return listOf<T>()
    }


    private fun generateInspectionData1(): InspectionEntity {

        var answerChoices1 = AnswerChoiceEntity(
            id = 3,
            name = "Everyday",
            score = 1.0
        )
        var answerChoices2 = AnswerChoiceEntity(
            id = 4,
            name = "Every two days",
            score = 0.5,
        )
        var answerChoices3 = AnswerChoiceEntity(
            id = 5,
            name = "Every week",
            score = 0.0
        )
        var answerChoices = realmListOf<AnswerChoiceEntity>()
        answerChoices.add(answerChoices1)
        answerChoices.add(answerChoices2)
        answerChoices.add(answerChoices3)

        val question = QuestionEntity(
            id = 1,
            name = "Is the drugs trolley locked?",
            answerChoices = answerChoices,
            selectedAnswerChoiceId = null,
        )

        val questions = realmListOf<QuestionEntity>()
        questions.add(question)


        val categories = CategoryEntity(
            id = 1,
            name = "Drugs",
            questions = questions
        )

        val category = realmListOf<CategoryEntity>()
        category.add(categories)

        val survey = SurveyEntity(
            id = 1,
            categories = category
        )


        val area = AreaEntity(
            id = 1,
            name = "Emergency ICU"
        )

        val inspectionType = InspectionTypeEntity(
            access = "write",
            id = 1,
            name = "Clinical"
        )

        return InspectionEntity(
            id = 1,
            area = area,
            inspectionType = inspectionType,
            survey = survey

        )
    }

    private fun generateInspectionData(): Inspection {

        var answerChoices1 = AnswerChoice(
            id = 3,
            name = "Everyday",
            score = 1.0
        )
        var answerChoices2 = AnswerChoice(
            id = 4,
            name = "Every two days",
            score = 0.5,
        )
        var answerChoices3 = AnswerChoice(
            id = 5,
            name = "Every week",
            score = 0.0
        )
        var answerChoices = mutableListOf<AnswerChoice>()
        answerChoices.add(answerChoices1)
        answerChoices.add(answerChoices2)
        answerChoices.add(answerChoices3)

        val question = Question(
            id = 1,
            name = "Is the drugs trolley locked?",
            answerChoices = answerChoices,
            selectedAnswerChoiceId = null,
        )

        val questions = mutableListOf<Question>()
        questions.add(question)


        val categories = Category(
            id = 1,
            name = "Drugs",
            questions = questions
        )

        val category = mutableListOf<Category>()
        category.add(categories)

        val survey = Survey(
            id = 1,
            categories = category
        )


        val area = Area(
            id = 1,
            name = "Emergency ICU"
        )

        val inspectionType = InspectionType(
            access = "write",
            id = 1,
            name = "Clinical"
        )

        return Inspection(
            id = 1,
            area = area,
            inspectionType = inspectionType,
            survey = survey

        )
    }
}
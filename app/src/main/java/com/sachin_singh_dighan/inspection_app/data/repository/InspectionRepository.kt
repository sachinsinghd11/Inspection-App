package com.sachin_singh_dighan.inspection_app.data.repository

import com.sachin_singh_dighan.inspection_app.data.api.NetworkService
import com.sachin_singh_dighan.inspection_app.data.model.AnswerChoice
import com.sachin_singh_dighan.inspection_app.data.model.Area
import com.sachin_singh_dighan.inspection_app.data.model.Category
import com.sachin_singh_dighan.inspection_app.data.model.Inspection
import com.sachin_singh_dighan.inspection_app.data.model.InspectionType
import com.sachin_singh_dighan.inspection_app.data.model.Question
import com.sachin_singh_dighan.inspection_app.data.model.Survey
import com.sachin_singh_dighan.inspection_app.utils.AppConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InspectionRepository @Inject constructor(private val networkService: NetworkService) {

    fun fetchInspections(): Flow<Inspection> {
        return flow {
            //emit(networkService.startInspection())
            emit(AppConstant.inspectionStartList)
        }.map {
            it
        }
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
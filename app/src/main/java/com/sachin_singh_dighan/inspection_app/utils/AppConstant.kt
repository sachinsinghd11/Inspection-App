package com.sachin_singh_dighan.inspection_app.utils

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

object AppConstant {
    const val NETWORK_ERROR = "Please Check Network Connection"


    private var answerChoices1 = realmListOf(
        AnswerChoiceEntity(
            id = 1,
            name = "Yes",
            score = 1.0
        ),
        AnswerChoiceEntity(
            id = 2,
            name = "No",
            score = 0.0,
        ),
        AnswerChoiceEntity(
            id = -1,
            name = "N/A",
            score = 0.0
        )
    )

    private var answerChoices2 = realmListOf(
        AnswerChoiceEntity(
            id = 3,
            name = "Everyday",
            score = 1.0
        ),
        AnswerChoiceEntity(
            id = 4,
            name = "Every two days",
            score = 0.5,
        ),
        AnswerChoiceEntity(
            id = 5,
            name = "Every week",
            score = 0.0
        )
    )

    private val questions1 = realmListOf(
        QuestionEntity(
            id = 1,
            name = "Is the drugs trolley locked?",
            answerChoices = answerChoices1,
            selectedAnswerChoiceId = null,
        ),
        QuestionEntity(
            id = 2,
            name = "How often is the floor cleaned?",
            answerChoices = answerChoices2,
            selectedAnswerChoiceId = null,
        ),
    )

    //question2

    private var answerChoices3 = realmListOf(
        AnswerChoiceEntity(
            id = 6,
            name = "1-2",
            score = 0.5
        ),
        AnswerChoiceEntity(
            id = 7,
            name = "3-6",
            score = 0.5,
        ),
        AnswerChoiceEntity(
            id = 8,
            name = "6+",
            score = 0.5
        ),
        AnswerChoiceEntity(
            id = -1,
            name = "N/A",
            score = 0.0
        ),

        )

    private var answerChoices4 = realmListOf(
        AnswerChoiceEntity(
            id = 9,
            name = "Very often",
            score = 0.5
        ),
        AnswerChoiceEntity(
            id = 10,
            name = "Often",
            score = 0.5,
        ),
        AnswerChoiceEntity(
            id = 11,
            name = "Not very often",
            score = 0.5,
        ),
        AnswerChoiceEntity(
            id = 12,
            name = "Never",
            score = 0.5,
        ),
    )

    private val questions2 = realmListOf(
        QuestionEntity(
            id = 3,
            name = "How many staff members are present in the ward?",
            answerChoices = answerChoices3,
            selectedAnswerChoiceId = null,
        ),
        QuestionEntity(
            id = 4,
            name = "How often are the area inspections carried?",
            answerChoices = answerChoices4,
            selectedAnswerChoiceId = null,
        ),
    )

    private val category = realmListOf(
        CategoryEntity(
            id = 1,
            name = "Drugs",
            questions = questions1
        ),
        CategoryEntity(
            id = 2,
            name = "Overall Impressions",
            questions = questions2
        ),
    )

    private val survey = SurveyEntity(
        id = 1,
        categories = category
    )


    private val area = AreaEntity(
        id = 1,
        name = "Emergency ICU"
    )

    private val inspectionType = InspectionTypeEntity(
        access = "write",
        id = 1,
        name = "Clinical"
    )

    val inspectionStartList = InspectionEntity(
        id = 1,
        area = area,
        inspectionType = inspectionType,
        survey = survey
    )

   /* private var answerChoices1 = listOf(
        AnswerChoice(
            id = 1,
            name = "Yes",
            score = 1.0
        ),
        AnswerChoice(
            id = 2,
            name = "No",
            score = 0.0,
        ),
        AnswerChoice(
            id = -1,
            name = "N/A",
            score = 0.0
        )
    )

    private var answerChoices2 = listOf(
        AnswerChoice(
            id = 3,
            name = "Everyday",
            score = 1.0
        ),
        AnswerChoice(
            id = 4,
            name = "Every two days",
            score = 0.5,
        ),
        AnswerChoice(
            id = 5,
            name = "Every week",
            score = 0.0
        )
    )

    private val questions1 = listOf(
        Question(
            id = 1,
            name = "Is the drugs trolley locked?",
            answerChoices = answerChoices1,
            selectedAnswerChoiceId = null,
        ),
        Question(
            id = 2,
            name = "How often is the floor cleaned?",
            answerChoices = answerChoices2,
            selectedAnswerChoiceId = null,
        ),
    )

    //question2

    private var answerChoices3 = listOf(
        AnswerChoice(
            id = 6,
            name = "1-2",
            score = 0.5
        ),
        AnswerChoice(
            id = 7,
            name = "3-6",
            score = 0.5,
        ),
        AnswerChoice(
            id = 8,
            name = "6+",
            score = 0.5
        ),
        AnswerChoice(
            id = -1,
            name = "N/A",
            score = 0.0
        ),

    )

    private var answerChoices4 = listOf(
        AnswerChoice(
            id = 9,
            name = "Very often",
            score = 0.5
        ),
        AnswerChoice(
            id = 10,
            name = "Often",
            score = 0.5,
        ),
        AnswerChoice(
            id = 11,
            name = "Not very often",
            score = 0.5,
        ),
        AnswerChoice(
            id = 12,
            name = "Never",
            score = 0.5,
        ),
    )

    private val questions2 = listOf(
        Question(
            id = 3,
            name = "How many staff members are present in the ward?",
            answerChoices = answerChoices3,
            selectedAnswerChoiceId = null,
        ),
        Question(
            id = 4,
            name = "How often are the area inspections carried?",
            answerChoices = answerChoices4,
            selectedAnswerChoiceId = null,
        ),
    )

    private val category = listOf(
        Category(
            id = 1,
            name = "Drugs",
            questions = questions1
        ),
        Category(
            id = 2,
            name = "Overall Impressions",
            questions = questions2
        ),
    )

    private val survey = Survey(
        id = 1,
        categories = category
    )


    private val area = Area(
        id = 1,
        name = "Emergency ICU"
    )

    private val inspectionType = InspectionType(
        access = "write",
        id = 1,
        name = "Clinical"
    )

    val inspectionStartList = Inspection(
        id = 1,
        area = area,
        inspectionType = inspectionType,
        survey = survey
    )*/
}
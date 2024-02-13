package com.example.androidtrivia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidtrivia.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    data class Question(val text: String, val answers: List<String>)

    private val questions: MutableList<Question> = mutableListOf(
        Question(text = "What does the acronym 'ADB' stand for in Android development?",
            answers = listOf("Android Debug Bridge", "Android Debugging", "Android Development Bridge", "Application Debug Bridge")),
        Question(text = "Which layout manager is used for creating a grid of equally sized cells in Android?",
            answers = listOf("GridLayoutManager", "LinearLayoutManager", "StaggeredGridLayoutManager", "FlexboxLayoutManager")),
        Question(text = "Which component is responsible for managing the lifecycle of an Android application?",
            answers = listOf("Activity", "Service", "BroadcastReceiver", "ContentProvider")),
        Question(text = "Which method is called to perform initial setup of an activity in Android?",
            answers = listOf("onCreate", "onStart", "onResume", "onStartCommand")),
        Question(text = "What is Android Jetpack?",
            answers = listOf("all of these", "tools", "documentation", "libraries")),
        Question(text = "Base class for Layout?",
            answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")),
        Question(text = "Layout for complex Screens?",
            answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")),
        Question(text = "Pushing structured data into a Layout?",
            answers = listOf("Data Binding", "Data Pushing", "Set Text", "OnClick")),
        Question(text = "Inflate layout in fragments?",
            answers = listOf("onCreateView", "onViewCreated", "onCreateLayout", "onInflateLayout")),
        Question(text = "Build system for Android?",
            answers = listOf("Gradle", "Graddle", "Grodle", "Groyle")),
        Question(text = "Android vector format?",
            answers = listOf("VectorDrawable", "AndroidVectorDrawable", "DrawableVector", "AndroidVector")),
        Question(text = "Android Navigation Component?",
            answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")),
        Question(text = "Registers app with launcher?",
            answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")),
        Question(text = "Mark a layout for Data Binding?",
            answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>"))

    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = 3

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentGameBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.game = this

        binding.btnSubmit.setOnClickListener{
            // checa o botão de rádio que foi selecionado pelo usuário.
            // se == -1, nenhum botao foi selecionado
            val checkedId = binding.rdGroupQuestion.checkedRadioButtonId
            if (checkedId != -1) {
                var answerIndex = 0
                when(checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3

                }
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    questionIndex++
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()

                        //  notificar a UI de que os dados foram atualizados e que a interface precisa ser redesenhada.
                        binding.invalidateAll()
                    } else {
                        // Navigate to the game won fragment
                    }
                } else {
                    // Navigate to the game over fragment
                }
            }
        }

        return binding.root
    }

    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.answers.toMutableList()
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.title_android_trivia_question,questionIndex+1,numQuestions)

    }

}
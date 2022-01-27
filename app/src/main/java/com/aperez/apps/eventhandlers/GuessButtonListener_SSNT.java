package com.aperez.apps.eventhandlers;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.aperez.apps.androidfunwithflags.MainActivityFragment_SSNT;
import com.aperez.apps.androidfunwithflags.R;
import com.aperez.apps.androidfunwithflags.ResultsDialogFragment_SSNT;
import com.aperez.apps.lifecyclehelpers.QuizViewModel;

public class GuessButtonListener_SSNT implements OnClickListener {
    private MainActivityFragment_SSNT mainActivityFragmentSSNT;
    private Handler handler;

    public GuessButtonListener_SSNT(MainActivityFragment_SSNT mainActivityFragmentSSNT) {
        this.mainActivityFragmentSSNT = mainActivityFragmentSSNT;
        this.handler = new Handler();
    }

    @Override
    public void onClick(View v) {
        Button guessButton = ((Button) v);
        String guess = guessButton.getText().toString();
        String answer = this.mainActivityFragmentSSNT.getQuizViewModel().getCorrectCountryName();
        this.mainActivityFragmentSSNT.getQuizViewModel().setTotalGuesses(1);

        if (guess.equals(answer)) {
            this.mainActivityFragmentSSNT.getQuizViewModel().setCorrectAnswers(1);
            this.mainActivityFragmentSSNT.getAnswerTextView().setText(answer + "!");
            this.mainActivityFragmentSSNT.getAnswerTextView().setTextColor(
                    this.mainActivityFragmentSSNT.getResources().getColor(R.color.correct_answer));

            this.mainActivityFragmentSSNT.disableButtons();

            if (this.mainActivityFragmentSSNT.getQuizViewModel().getCorrectAnswers()
                    == QuizViewModel.getFlagsInQuiz()) {
                ResultsDialogFragment_SSNT quizResults = new ResultsDialogFragment_SSNT();
                quizResults.setCancelable(false);
                try {
                    quizResults.show(this.mainActivityFragmentSSNT.getChildFragmentManager(), "Quiz Results");
                } catch (NullPointerException e) {
                    Log.e(QuizViewModel.getTag(),
                            "GuessButtonListener_SSNT: this.mainActivityFragmentSSNT.getFragmentManager() " +
                                    "returned null",
                            e);
                }
            } else {
                this.handler.postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                mainActivityFragmentSSNT.animate(true);
                            }
                        }, 2000);
            }
        } else {
            this.mainActivityFragmentSSNT.incorrectAnswerAnimation();
            guessButton.setEnabled(false);
        }
    }
}

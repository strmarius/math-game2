package app.math;

public record AboutAppText() {

    public String aboutApp() {
        return """
                Basic Math game for kids with following features:

                - 2 random generated numbers and ask for result
                - Feedback based on result
                - Option to set maximum value for generated number (difficulty)

                _________________

                v1.01

                - Set focus for answerField
                - Use ENTER key as shortcut for submitButton
                - MaxNum is also max sum(result) beside generated numbers
                - Change random generator from function Math.random to class Random
                - Condition Random generator not to give same numbers as previous exercise

                v1.02

                - Set MenuBar
                - Add Config in MenuBar for MaxNumber, MinNumber
                - Solve a bug (if you enter max value = 1 program crash)
                - Solve bound for random (EX: for max = 2, you get only 0+0, 1+0 and 0+1)

                v1.03

                - Add AboutApp menu
                - Add config file for app to read&write settings which is saved at close of app
                - Remove initial set for MaxNum
                - If cfg missing/wrong data set min=0 & max=10
                - Add option of setting \"Player\" name to have personal feedback
                
                v1.04
                
                - Separate app in classes for a easier upgrade and use of code(classes: UI, RandomGenerator, AboutApp)
                - Improve UI with new colors
                - Extend feedback label for better readability
                - Add table score
                - Solve a bug(setMin and setMax num in menuBar were set even if condition occur with an error
                        Solution: Register userInput in a var before set de min/max)
                - Add scroll bar to this menu because of long content
                
                """;
    }
}

import 'package:'

class QuestionController {
  double correctValue;
  // UserController _userController = locator.get<UserController>();

  QuestionController({required this.correctValue});

  List<int> generatePossibleOptions(double correctValue){
    List<int> optionsFromValue = [
      (0.25*correctValue).round(),
      (0.5*correctValue).round(),
      (0.75*correctValue).round(),
      (1.25*correctValue).round(),
      (1.75*correctValue).round(),
      (2*correctValue).round()
    ];

    return optionsFromValue;
  }

  List<int> getChoices(double correctValue){
    List<int> storeChoices = [];

    List<int> optionsFromValue = generatePossibleOptions(correctValue);
    // Using the cascade operator("..") in this instance is equivalent to: 
    // List<int> shuffledList = optionsFromValue; shuffledList.shuffle();
    List<int> shuffledList = optionsFromValue..shuffle(); 
    for(int i = 0; i < 3; i++)[
      storeChoices.add(shuffledList[i]);
    ]
    storeChoices.add(correctValue.round());
    List<int> choices = storeChoices..shuffle();
    return choices;
  }

}

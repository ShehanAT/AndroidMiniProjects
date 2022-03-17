class QuestionController {
  double correctValue;
  UserController _userController = locator.get<UserController>();

  QuestionController({this.correctValue});
}

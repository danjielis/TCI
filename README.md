# Testing and Continuous Integration 
## Fontys University of Applied Sciences - Software Engineering program


Following repository contains 3 student work for their group deliverable. It is an exciting look at Java code testing with JUnit, and Test Driven Development model within the scenario of contemporary Dutch casino regulation.


## Getting Started

You can clone project from the GitHub website or prefered CLI, since it is public.


### Prerequisites

Team recommends to use IntelliJ Idea IDE, with given gradle configuration.

Dependecies can be observed in this list:
    implementation 'junit:junit:4.12'
    testCompile group: 'junit', name: 'junit', version: '4.12'
    compile 'javax.jms:javax.jms-api:2.0.1'
    compile 'org.apache.activemq:activemq-all:5.15.3'
    compile 'com.google.code.gson:gson:2.8.4'
    compile group: 'pl.pragmatists', name: 'JUnitParams', version: '1.0.4'
    compile 'org.mockito:mockito-core:2.+'
    compile 'org.easytesting:fest-assert:1.4'
    compile 'org.hamcrest:hamcrest-all:1.3'

    Java source compatiblity: 1.8

### Installing and running tests

Only setting up that you might have to do, based on your IDE and Gradle setting is the test run configuration. In order to so select Run -> Configuration -> Create Configuration. Then enter name and select for it to apply for the entire project. Once done in the upper selection bar find configuration to run all tests. This is only applicable if IDE will not pick up the configuration file by default.


## Built With

Refer to dependencies.

#### Tests done

| 		  | Source Code   | Test                                                                                |
|---------|---------------|-------------------------------------------------------------------------------------|
| 		  | Casino        | addGame_GetGameByName_ReturnSameGame()                                              |
|         |               | addgameNameIsNullOrEmpty_ThrowIllegalArgumentException()                            |
|         |               | addMultipleGames_GetListOfGames_IsCopyOfCreatedGames()                              |
|         |               | addNewGame_GetListOfGames_ListIsNotEmpty()                                          |
|         |               | createCasino_CasinoContainsBetLoggingAuthorityAndBetTokenAuthority_True()           |
|         | GamingMachine | acceptWinner_AmountInPlayerCardIsUpdatedByCashier_MethodIsCalled()                  |
|         |               | acceptWinner_GetOpenBets_IsEmpty()                                                  |
|         |               | acceptWinner_WinningBetIsNotSameAsPlacedBet_ThrowIllegalArgument()                  |
|         |               | connectCard_GetPlayerCard_True()                                                    |
|         |               | newGamingMachine_GetOpenBets_Empty()                                                |
|         |               | newGamingMachine_GetPlayerCard_ThrowNoPlayerCardException()                         |
|         |               | newGamingMachine_IdIsCreatedByIDFactory_True()                                      |
|         |               | newGamingMachine_ReturnUniqueGamingMachineID_True()                                 |
|         |               | placeBet_BetAmountNotLargerThan0OrNull_ThrowIllegalArgumentException​               |
|         |               | placeBet_CashierDoesNotExceptBet_BetIsNotStored_Throw_BetNotExceptedException                                   |
|         |               | placeBet_CheckThatPlayerCardHasEnoughMoney_MethodIsCalled()                         |
|         |               | placeBet_GameDoesNotExceptBet_BetIsNotStored_Throw_NoCurrentRoundException()                                   |
|         |               | placeBet_GamingMachineAlreadyHaveABet_Prevent()                                     |
|         |               | placeBet_GamingMachineHasNoPlayerCard_ThrowNoPlayerCardException()                  |
|         |               | placeBet_GetOpenBets_NotEmpty()                                                     |
|         |               | placeBet_SubmitToGamingMachine_True()                                               |
|         | IDFactory     | idWithTypeX_IsDescribedByUUIDAndTimeStamp​()                                        |
|         |               | newGeneralID_IsDescribedByUUIDAndTimeStamp()                                        |
|         |               | newGeneralID_TimestampIsNow_True()                                                  |
|         |               | newGeneralIDType_CaseInsensitive_True​()                                            |
|         |               | newGeneralIDTypeNull_ReturnNull_True()                                              |
|         |               | newIdWithTypeX_IsInstanceOfGeneralID_True​()                                        |
|         |               | afterCreatingTwoIDs_TheyCanBeComparedByTimestamp_True()                             |
|---------|---------------|-------------------------------------------------------------------------------------|

## Contributing

Contributions are not expected after course, please use it freely if you deem it educational.


## Authors

* **Yoanna Borisova**
* **Dongdong Ke**
* **Danas Jusys**

* **Under the guidance of teacher Erik**


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to TDD folks

* Inspiration

'''
Builds the PlainText String block for the Multipart Email Message
'''
class PlainTextBuilder:
    # region Initialisation
    def __init__(self):
        self.__initialiseVariables()

    def __initialiseVariables(self):
        self.__testReportUrlPattern = 'https://addons-testing.bitrise.io/builds/{BUILD_SLUG}/summary?status=all'
        self.__buildUrlPattern = 'https://app.bitrise.io/build/{BUILD_SLUG}'
    # endregion

    # region Helper Methods
    def __buildIntroText(self, buildSlug, currentTime):
        '''
        Builds the Introduction text to the email
        :param buildSlug: Bitrise Build Slug
        :param currentTime: String of current Date time
        :return: Introduction String
        '''
        text = 'PlantTester App\n'
        text += 'Test Results for build ' + buildSlug + ' at ' + currentTime + '\n'
        return text

    def __buildCumulativeResultsBlock(self, resultsDict):
        '''
        Builds the Cumulative Results text block
        :param resultsDict: Dictionary of Results
        :return: Results String
        '''
        text = '\n'
        text += '+== Cumulative Results ==+\n'
        for eachKey in resultsDict.keys():
            text += str(eachKey) + ' : ' + resultsDict[eachKey] + '\n'
        text += '+========================+\n'
        return text

    def __buildLogCatsReports(self, logCatDict):
        '''
        Builds the text block of each test case and their steps
        :param logCatDict: Dictionary of LogCat Data
        :return: Logcat tests String
        '''
        text = '\n'
        for eachKey in logCatDict.keys():
            text += '+== ' + str(eachKey) + ' ==+\n'
            i = 1
            for eachStep in logCatDict[eachKey]:
                text += str(i) + ') ' + str(eachStep) + '\n'
            text += '+===' + (len(eachKey) * '=') + '===+\n'
        return text

    def __buildEndLinks(self, buildSlug):
        '''
        Builds the end links of the email text
        :param buildSlug: Bitrise Build Slug
        :return: End Links String
        '''
        testUrl = self.__testReportUrlPattern.replace('{BUILD_SLUG}', buildSlug)
        buildUrl = self.__buildUrlPattern.replace('{BUILD_SLUG}', buildSlug)
        text = '\n'
        text += 'Find out more about the tests at : ' + testUrl + '\n'
        text += 'Find out more about the build at : ' + buildUrl + '\n'
        return text
    # endregion

    def buildTextBlock(self, buildSlug, currentTime, resultsDict, logCatDict):
        '''
        Builds the PlainText block for the Multipart Email Message
        :param buildSlug: Bitrise Build Slug
        :param currentTime: String of current date time
        :param resultsDict: Dictionary of cumulative results
        :param logCatDict: Dictionary of LogCat test cases and their steps
        :return: String for the PlainText block
        '''
        textStr = self.__buildIntroText(buildSlug, currentTime)
        textStr += self.__buildCumulativeResultsBlock(resultsDict)
        textStr += self.__buildLogCatsReports(logCatDict)
        textStr += self.__buildEndLinks(buildSlug)
        return textStr



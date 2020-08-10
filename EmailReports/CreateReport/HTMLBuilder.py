'''
Builds the HTML String block for the Multipart Email Message
'''
class HTMLBuilder:
    # region Initialisation
    def __init__(self):
        self.__initialiseVariables()

    def __initialiseVariables(self):
        self.__testReportUrlPattern = 'https://addons-testing.bitrise.io/builds/{BUILD_SLUG}/summary?status=all'
        self.__buildUrlPattern = 'https://app.bitrise.io/build/{BUILD_SLUG}'

    # endregion

    # region Helper Methods
    def __buildBasicHtmlStructure(self):
        '''
        Builds the HTML text blcok for Multipart message of email
        :return: HTML String
        '''
        html = '<html>\n'
        html += '<body>\n'
        html += '<style>\n'
        html += 'table, th, td {\n'
        html += '  border: 1px solid black;\n'
        html += '}\n'
        html += '</style>\n'
        html += '{TITLE_OF_REPORT}\n'
        html += '{CUMULATIVE_RESULTS_TABLE}\n'
        html += '{INFO_ON_REPORT}\n'
        html += '{TEST_TABLES}\n'
        html += '</body>\n'
        html += '</html>\n'
        return html

    def __buildReportTitle(self):
        '''
        Builds the HTML block for the report title
        :return: HTML String for Title
        '''
        html = '<h1>\n'
        html += 'PlantTester : App Test Results\n'
        html += '</h1>\n'
        return html

    def __buildReportInfo(self, buildSlug, currentTime):
        '''
        Builds the HTML block for the Report Information
        :param buildSlug: Bitrise Build Slug
        :param currentTime: String of current date time
        :return: HTML String for Report Information
        '''
        html = '<br>\n'
        html += '<p>\n'
        html += 'PlantTester UI test results for the build ' + buildSlug + ' at ' + currentTime + '. \n'
        html += 'You can find more about the build '
        html += '<a href="' + self.__buildUrlPattern.replace('{BUILD_SLUG}', buildSlug)+ '">here</a>,\n'
        html += 'and you can see the test results in detail '
        html += '<a href="' + self.__testReportUrlPattern.replace('{BUILD_SLUG}', buildSlug)+ '">here</a>.\n'
        html += '</p>\n'
        html += '<br>\n'
        return html

    def __buildCumulativeResultsTable(self, resultsDict):
        '''
        Builds the HTML block for the Cumulative Results Table
        :param resultsDict: Dictionary of Cumulative Results
        :return: HTML String for Cumulative Results table
        '''
        html = '<br>\n'
        html += '<center>\n'
        html += '<table style="width:25%", border="1">\n'
        html += '<th colspan="2"><bold>Test Results</bold></th>\n'
        for eachKey in resultsDict.keys():
            eachItem = str(eachKey)
            eachValue = str(resultsDict[eachKey])
            html += '<tr>\n'
            html += '<td>' + eachItem + '</td>\n'
            html += '<td>' + eachValue + '</td>\n'
            html += '</tr>\n'
        html += '</table>\n'
        html += '</center>\n'
        return html

    def __buildTestTables(self, logCatDict):
        '''
        Builds the HTML Block for tables of individual test cases and their steps
        :param logCatDict: Logcat Dictionary of test cases and their steps
        :return:
        '''
        html = '<br>\n'
        html += '<h2>\n'
        html += '<center>\n'
        html += 'Test Cases\n'
        html += '</center>\n'
        html += '</h2>\n'

        for eachCase in logCatDict.keys():
            eachCaseName = str(eachCase)
            html += '<table style="width:50%", border="1">\n'
            html += '<th colspan="2"><bold>'+ eachCaseName +'</bold></th>\n'
            i = 1
            for eachStep in logCatDict[eachCase]:
                eachStepStr = str(eachStep)
                html += '<tr>\n'
                if 'ERROR' in eachStepStr:
                    html += '<font style="color:red">\n'
                html += '<td>' + str(i) + '.</td>\n'
                html += '<td>' + eachStepStr + '</td>\n'
                if 'ERROR' in eachStepStr:
                    html += '</font>\n'
                html += '</tr>\n'
                i += 1
            html += '</table>\n'
            html += '<br>\n'

        return html
    # endregion

    def buildHtmlBlock(self, buildSlug, currentTime, resultsDict, logCatDict):
        '''
        Builds the HTML block for the Multipart Email Message
        :param buildSlug: Bitrise Build Slug
        :param currentTime: String of current date time
        :param resultsDict: Dictionary of cumulative results
        :param logCatDict: Dictionary of LogCat test cases and their steps
        :return: String for the HTML block
        '''
        htmlStr = self.__buildBasicHtmlStructure()
        htmlStr = htmlStr.replace('{TITLE_OF_REPORT}', self.__buildReportTitle())
        htmlStr = htmlStr.replace('{CUMULATIVE_RESULTS_TABLE}', self.__buildCumulativeResultsTable(resultsDict))
        htmlStr = htmlStr.replace('{INFO_ON_REPORT}', self.__buildReportInfo(buildSlug, currentTime))
        htmlStr = htmlStr.replace('{TEST_TABLES}', self.__buildTestTables(logCatDict))
        return htmlStr



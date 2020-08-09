#TODO
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

        :return:
        '''
        html = '<html>\n'
        html += '<body>\n'
        html += '{TITLE_OF_REPORT}\n'
        html += '{CUMULATIVE_RESULTS_TABLE}\n'
        html += '{INFO_ON_REPORT}\n'
        html += '{TEST_TABLES}\n'
        html += '</body>\n'
        html += '</html>\n'
        return html

    # TODO
    def __buildReportTitle(self, buildSlug):
        '''

        :param buildSlug:
        :return:
        '''
        return ''

    # TODO
    def __buildReportInfo(self, buildSlug, currentTime):
        '''

        :param buildSlug:
        :param currentTime:
        :return:
        '''
        return ''

    # TODO
    def __buildCumulativeResultsTable(self, resultsDict):
        '''

        :param resultsDict:
        :return:
        '''
        return ''

    # TODO
    def __buildTestTables(self, logCatDict):
        '''

        :param logCatDict:
        :return:
        '''
        return ''
    # endregion

    def buildHtmlBlock(self, buildSlug, currentTime, resultsDict, logCatDict):
        '''

        :param buildSlug:
        :param currentTime:
        :param resultsDict:
        :param logCatDict:
        :return:
        '''
        htmlStr = self.__buildBasicHtmlStructure()
        htmlStr = htmlStr.replace('{TITLE_OF_REPORT}', self.__buildReportTitle(buildSlug))
        htmlStr = htmlStr.replace('{CUMULATIVE_RESULTS_TABLE}', self.__buildCumulativeResultsTable(resultsDict))
        htmlStr = htmlStr.replace('{INFO_ON_REPORT}', self.__buildReportInfo(buildSlug, currentTime))
        htmlStr = htmlStr.replace('{TEST_TABLES}', self.__buildTestTables(logCatDict))
        return htmlStr



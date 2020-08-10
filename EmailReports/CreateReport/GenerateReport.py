'''
Main class to collate results and logcats and generate report email body (Both PlainText and HTML)
'''
# region Imports
import os
import importlib
from datetime import datetime

from CreateReport import GatherLogCatData as GLCD
from CreateReport import GatherResultsData as GRD
from CreateReport import PlainTextBuilder as PTB
from CreateReport import HTMLBuilder as HTMLB

importlib.reload(GLCD)
importlib.reload(GRD)
importlib.reload(PTB)
importlib.reload(HTMLB)
# endregion

class GenerateReport:
    # region Initialisation
    def __init__(self):
        self.__initialiseClasses()
        self.__initialiseVariables()

    def __initialiseVariables(self):
        self.__testReportUrlPattern = 'https://addons-testing.bitrise.io/builds/{BUILD_SLUG}/summary?status=all'
        self.__logCatDict = {}
        self.__resultsDict = {}
        self.__emailBody = {}

    def __initialiseClasses(self):
        self.__logCatReader = GLCD.GatherLogCatData()
        self.__resultReader = GRD.GatherResultsData()
        self.__plainTextBuilder = PTB.PlainTextBuilder()
        self.__htmlBuilder = HTMLB.HTMLBuilder()
    # endregion

    # region Data Collection Helper Methods
    def __getLogCatData(self, listOfLogcatPaths):
        '''
        Generates the LogCat dictionary for the various test cases logcat files
        :param listOfLogcatPaths: List of Path Strings of the test cases logcats
        :return: None
        '''
        for eachLogCat in listOfLogcatPaths:
            testTitle, testSteps = self.__logCatReader.getReportData(eachLogCat)
            self.__logCatDict[testTitle] = testSteps

    def __getResultsData(self, xmlPath):
        '''
        Generates the test results dictionary from the test results xml file
        :param xmlPath: Path String of the test results xml
        :return: None
        '''
        self.__resultsDict = self.__resultReader.getResultsXmlData(xmlPath)

    def __gatherTestArtifactsData(self, xmlPath, listOfLogcatPaths):
        '''
        Gathers data from the test artifacts
        :param xmlPath: Path String of the test results xml
        :param listOfLogcatPaths:  List of Path Strings for the test cases logcats
        :return: None
        '''
        self.__getResultsData(xmlPath)
        self.__getLogCatData(listOfLogcatPaths)
    # endregion

    # region Report Email Body Generation
    def __gatherBuildInfo(self):
        '''
        Gathers build information from the Bitrise Environment Variables
        -> Build URL
        -> Build Slug
        -> Test Report URL
        :return: None
        '''
        self.__buildUrl = str(os.environ['BITRISE_BUILD_URL'])
        self.__buildSlug = self.__buildUrl.split('/')[-1].strip()
        self.__testReportPageUrl = self.__testReportUrlPattern.replace('{BUILD_SLUG}',
                                                                       self.__buildSlug)

    def __getCurrentTimeString(self):
        '''
        Get the string of current date time in the {%a %d/%b/%Y, %H:%M:%S} format
        For Eg, {Mon 10/Aug/2020, 20:03:53}
        :return: String of Current date time
        '''
        return datetime.now().strftime("%a %d/%b/%Y, %H:%M:%S")

    def __generateEmailBody(self):
        '''
        Generates the HTML and PlainText Body for the Report Email
        :return: None
        '''
        self.__gatherBuildInfo()
        currentTime = self.__getCurrentTimeString()
        self.__emailBody['text'] = self.__plainTextBuilder.buildTextBlock(self.__buildSlug, currentTime,
                                                                          self.__resultsDict, self.__logCatDict)
        self.__emailBody['html'] = self.__htmlBuilder.buildHtmlBlock(self.__buildSlug, currentTime,
                                                                          self.__resultsDict, self.__logCatDict)
    # endregion

    def generateReport(self, xmlPath, listOfLogcatPaths):
        '''
        Collates data from the test results xml and various test cases logcats to generate the body, both PlainText
        and HTML parts, of the MIMEMultipart message to be sent to the reciever email ids
        :param xmlPath: Path String of the test results xml
        :param listOfLogcatPaths: List of Path Strings of the test cases logcats
        :return: Dictionary with the following key-value pairs
            -> 'text' : PlainText String block for the PlainText part of the Email
            -> 'html' : HTML String block for the HTML part of the Email
        '''
        self.__gatherTestArtifactsData(xmlPath, listOfLogcatPaths)
        self.__generateEmailBody()
        return self.__emailBody














#TODO
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

    def generateReport(self, xmlPath, listOfLogcatPaths):
        self.__gatherTestArtifacts(xmlPath, listOfLogcatPaths)
        self.__generateEmailBody()
        return self.__emailBody


    def __gatherTestArtifacts(self, xmlPath, listOfLogcatPaths):
        self.__getResultsData(xmlPath)
        self.__getLogCatData(listOfLogcatPaths)

    def __generateEmailBody(self):
        currentTime = self.__getCurrentTimeString()
        self.__emailBody['text'] = self.__plainTextBuilder.buildTextBlock(self.__buildSlug, currentTime,
                                                                          self.__resultsDict, self.__logCatDict)
        self.__emailBody['html'] = self.__htmlBuilder.buildHtmlBlock(self.__buildSlug, currentTime,
                                                                          self.__resultsDict, self.__logCatDict)


    def __getLogCatData(self, listOfLogcatPaths):
        for eachLogCat in listOfLogcatPaths:
            testTitle, testSteps = self.__logCatReader.getReportData(eachLogCat)
            self.__logCatDict[testTitle] = testSteps

    def __getResultsData(self, xmlPath):
        self.__resultsDict = self.__resultReader.getResultsXmlData(xmlPath)

    def __getCurrentTimeString(self):
        '''
        Get the string of current date time in the {%a %d/%b/%Y, %H:%M:%S} format
        For Eg, {Mon 10/Aug/2020, 20:03:53}
        :return: String of Current date time
        '''
        return datetime.now().strftime("%a %d/%b/%Y, %H:%M:%S")



    def __gatherBuildInfo(self):
        self.__buildUrl = str(os.environ['BITRISE_BUILD_URL'])
        self.__buildSlug = self.__buildUrl.split('/')[-1].strip()
        self.__testReportPageUrl = self.__testReportUrlPattern.replace('{BUILD_SLUG}',
                                                                       self.__buildSlug)


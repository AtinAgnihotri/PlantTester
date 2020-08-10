'''
Collects the UI Test Artifacts and
generates a report before emailing the same to a select list of members

Created by Atin Agnihotri for PlantTester App
'''
# region Imports
import os
import importlib
import re

from CreateReport import GenerateReport as WR
from SendEmail import EmailReport as ER

importlib.reload(WR)
importlib.reload(ER)
# endregion


class ReportHandler:
    # region Initialisation
    def __init__(self):
        self.__initialiseClasses()
        self.__initialiseVariables()
        print('-> Initialised Report Handler')

    def __initialiseClasses(self):
        self.reporter = WR.GenerateReport()
        self.sender = ER.EmailReport()

    def __initialiseVariables(self):
        self.__listOfLogCatPaths = []
        self.__reportData = ''
        self.__resultsXmlPattern = r'[a-zA-Z1-9-]+_(test_result)_\d(\.xml)'
        self.__testCaseLogcatPattern = r'[a-zA-Z1-9-]+(_test_cases_)\d{4}(_logcat)'
    # endregion

    # region Setup Helper Methods
    def __findLogCats(self):
        '''
        Finds the list of logcat paths that need to be processed for report generation
        :return: List of Path Strings of the test cases logcats
        '''
        listOfLogCatPaths = []
        for dirPaths, dirNames, fileNames in os.walk(self.__testArtifactsDir):
            for eachFile in fileNames:
                # isLogCat = eachFile.endswith('logcat')
                # isTestCase = 'test_cases' in eachFile
                # if isLogCat and isTestCase:
                if re.findall(self.__testCaseLogcatPattern, eachFile):
                    eachLogCatPath = os.path.join(dirPaths, eachFile).replace('\\','/')
                    listOfLogCatPaths.append(eachLogCatPath)
        return listOfLogCatPaths

    def __findResultsXml(self):
        '''
        Finds the Test Results XML file
        :return: Path String of the results xml
        '''
        xmlPath = ''
        for eachFile in os.listdir(self.__testArtifactsDir):
            # isXml = eachFile.endswith('.xml')
            # isTestResult = 'test_result' in eachFile
            # if isXml and isTestResult:
            if re.findall(self.__resultsXmlPattern, eachFile):
                xmlPath = os.path.join(self.__testArtifactsDir, eachFile).replace('\\','/')
                break
        return xmlPath

    def __getResultsDir(self):
        '''
        Gets the Test Artifacts directory pulled from Device Testing step
        :return: None
        '''
        self.__testArtifactsDir = str(os.environ['VDTESTING_DOWNLOADED_FILES_DIR'])

    # endregion

    # region Main Helper Methods
    def __generateReports(self):
        '''
        Finds the test artifacts and Generates the Report Body Dictionary for email,
        with values keyed to 'text' and 'html' blocks
        :return: None
        '''
        self.__getResultsDir()
        self.__reportData = self.reporter.generateReport(
            self.__findResultsXml(),
            self.__findLogCats()
        )

    def __sendEmails(self):
        '''
        Sends the generated email body to the list of reciever email ids
        :return: None
        '''
        self.sender.sendReports(self.__reportData)
    # endregion

    def generateAndSendReport(self):
        '''
        Generates the report body for email and then sends the same to a list of
        recievers email ids
        :return: None
        '''
        self.__generateReports()
        print('-> Generated Test Reports')
        self.__sendEmails()
        print('-> Sent Test Reports')


if __name__ == '__main__':
    print ('+~~ Running EmailReports for PlantTesters ~~+')
    inst = ReportHandler()
    inst.generateAndSendReport()
    print('+~~~~~~~~~~~ Closing EmailReports ~~~~~~~~~~~+')



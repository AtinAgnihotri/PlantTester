#TODO
'''
Collects the UI Test Artifacts and
generates a report before emailing the same to a select list of members
'''

import os
import importlib


from CreateReport import GenerateReport as WR
from SendEmail import EmailReport as ER

importlib.reload(WR)
importlib.reload(ER)



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
        self.reportData = ''
    # endregion

    # region TODO Setup Helper Methods
    def __setupNecessaryModules(self):
        pass

    def __findLogCats(self):
        return []

    def __findResultsXml(self):
        return ''

    def __getResultsDir(self):
        self.resultsDir = ''
    # endregion

    # region Main Helper Methods
    def __generateReports(self):
        '''
        Generates the Report Body Dictionary for email, with values keyed to
        'text' and 'html' blocks
        :return: None
        '''
        self.reportData = self.reporter.generateReport(
            self.__findResultsXml(),
            self.__findLogCats()
        )

    def __sendEmails(self):
        '''
        Sends the generated email body to the list of reciever email ids
        :return: None
        '''
        self.sender.sendReports(self.reportData)
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
    # inst.generateAndSendReport()
    print('+~~~~~~~~~~~ Closing EmailReports ~~~~~~~~~~~+')



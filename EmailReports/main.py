#TODO
'''
Collects the UI Test Artifacts and
generates a report before emailing the same to a select list of members
'''

import os
import importlib


from CreateReport import WriteReport as WR
from SendEmail import EmailReport as ER

importlib.reload(WR)
importlib.reload(ER)



class ReportGenerator:
    def __init__(self):
        self.__initialiseClasses()
        self.__initialiseVariables()

    def __initialiseClasses(self):
        self.reporter = WR.WriteReport()
        self.sender = ER.EmailReport()

    def __initialiseVariables(self):
        self.__listOfLogCatPaths = []
        self.reportData = ''

    def generateAndSendReport(self):
        self.__generateReports()
        self.__sendEmails()

    def __findLogCats(self):
        pass

    def __generateReports(self):
        self.reportData = self.reporter.writeReportsForLogCats()

    def __sendEmails(self):
        self.sender.sendReports(self.reportData)

if __name__ == '__main__':
    inst = ReportGenerator()
    inst.generateAndSendReport()


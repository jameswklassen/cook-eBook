# cook-eBook
Software Engineering Summer 2019 Project created by the Master Branch Chefs

# Master Branch Chefs Contributing members
<table>
<tr>
    <td style="text-align: center;">
        <a href="https://code.cs.umanitoba.ca/jamesklassen">
            <img src="https://code.cs.umanitoba.ca/uploads/-/system/user/avatar/182/avatar.png?width=400" width="100px;"/>
            <br/>
            <sub>
                <b>James Klassen </b>
            </sub>
        </a>
    </td>
    <td style="text-align: center;">
        <a href="https://code.cs.umanitoba.ca/raquelthiessen">
            <img src="https://code.cs.umanitoba.ca/uploads/-/system/user/avatar/175/avatar.png?width=400" width="100px;"/>
            <br/>
            <sub>
                <b>Raquel Thiessen</b>
            </sub>
        </a>
    </td>
    <td style="text-align: center;">
        <a href="https://code.cs.umanitoba.ca/RyanF">
            <img src="https://code.cs.umanitoba.ca/uploads/-/system/user/avatar/176/avatar.png?width=400" width="100px;"/>
            <br/>
            <sub>
                <b>Ryan Froese</b>
            </sub>
        </a>
    </td>
    <td style="text-align: center;">
        <a href="https://code.cs.umanitoba.ca/OfficialArms">
            <img src="https://code.cs.umanitoba.ca/uploads/-/system/user/avatar/179/avatar.png?width=400" width="100px;"/>
            <br/>
            <sub>
                <b>Joshua Moreira</b>
            </sub>
        </a>
    </td>
    <td style="text-align: center;">
        <a href="https://code.cs.umanitoba.ca/RuoshiZhao">
            <img src="https://code.cs.umanitoba.ca/uploads/-/system/user/avatar/209/avatar.png?width=400" width="100px;"/>
            <br/>
            <sub>
                <b>Ruoshi Zhao</b>
            </sub>
        </a>
</tr>
</table>

# Branching
There will be two main branches in this project: The master branch and the develop branch. All development of tasks is done in task branches that are made off of the develop branch.

Task branches will be named in the following format: TASK-name-or-description-here

When you feel the code for your task is done, you can put up a merge request to have your branch merged into the Develop branch. The only circumstances in which a merge to master is permitted is when we're cutting a release for an iteration (or, god forbid, a critical hotfix).

# Merge Requests
All merge requests should have an appropriate title, consisting of a brief description of what was changed/added. In the description should be a more detailed version, including any and all important/relevant technical details.

You should link user story and what developer task it is assoicated with.

As an option in your merge request you should check off `squash commits` and `delete source branch`

In terms of approvals, this will depend on the scope of the request. If you're just adding a few lines of documentation, you don't need any approvals. If you're adding any functional code, you need at least 1 approval, from someone other than yourself. If you're adding a significant amount of code, then you'll need 2 approvals. Significant additions SHOULD and WILL require unit tests to ensure that the changes are working.

Additionally, before your merge you should merge develop into your task branch and do a final sanity check to make sure everything works as expected. Then, assuming you have your approvals, you can merge.

# Unit Tests
As said before, any large functional change will require a unit test. Generally, if there's a publically facing method in a class, it should be unit tested. Even the most basic of unit tests are useful, though everybody needs to make an effort to have unit tests comprehensively test all publically facing functionality.

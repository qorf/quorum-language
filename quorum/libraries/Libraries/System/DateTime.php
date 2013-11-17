<?php $classPageTitle = "DateTime"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.System.DateTime</h1>
<input type="hidden" id="classkey" value="Libraries.System.DateTime" /><h2> <span class="controllable" data-componentType="class-name">Class DateTime</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The DateTime class is used to gather information about date and time on the system, or from a specified date/time. On creation, this object will return information pertaining to the current date and time as reported by the system. This class can also be used to gather information about a different date and time using the standard epoch time and the SetEpochTime() action. If SetEpochTime() is not called, information about the current date and time will be returned by the various functions.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.System.DateTime
class Main
   action main
      DateTime datetime
      // Print the current date and time.
      output datetime:GetMonth() + " " + datetime:GetDayOfMonth() + ", " + datetime:GetYear() + " " datetime:GetHour() + ":" + datetime:GetMinutes()
   end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDayOfMonth">public system action GetDayOfMonth()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDayOfMonth"><p>This action returns the day of the month represented by this DateTime object. The day is in the range 1 to 31, inclusive.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the day of the month</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDayOfMonth"><em>Example Code:</em></span>
<pre class="code">DateTime datetime
output datetime:GetDayOfMonth()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDayOfWeek">public system action GetDayOfWeek()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDayOfWeek"><p>This action returns the day of the week represented by this DateTime object. The day is in the range 1 to 7, inclusive.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the day of the week</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDayOfWeek"><em>Example Code:</em></span>
<pre class="code">DateTime datetime
output datetime:GetDayOfWeek()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetEpochTime">public system action GetEpochTime()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetEpochTime"><p>This action returns the elapsed milliseconds since the epoch, January 1, 1970 00:00:00 GMT, as reported by the system. This time can be used to store a date and use it at a later time to retrieve information such as the hour. This action will always return the current epoch time, regardless of whether or not SetEpochTime() has been called.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the elapsed time since the epoch as reported by the system.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetEpochTime"><em>Example Code:</em></span>
<pre class="code">DateTime datetime
output datetime:GetEpochTime()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetHour">public system action GetHour()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetHour"><p>This action returns the hour represented by this DateTime object. The hour is in the range 0 to 23, where 0-12 represent the times 12 AM to 12 PM, and 13-23 represent 1 PM to 11 PM.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the hour</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetHour"><em>Example Code:</em></span>
<pre class="code">DateTime datetime
output datetime:GetHour()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMinute">public system action GetMinute()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMinute"><p>This action returns the minute represented by this DateTime object. The minute is in the range 0 to 59, inclusive.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the minute</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMinute"><em>Example Code:</em></span>
<pre class="code">DateTime datetime
output datetime:GetMinute()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMonth">public system action GetMonth()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMonth"><p>This action returns the month represented by this DateTime object. The month is in the range 1 to 12, inclusive.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the month</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMonth"><em>Example Code:</em></span>
<pre class="code">DateTime datetime
output datetime:GetMonth()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSecond">public system action GetSecond()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSecond"><p>This action returns the second represented by this DateTime object. The second is in the range 0 to 59, inclusive.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the second</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSecond"><em>Example Code:</em></span>
<pre class="code">DateTime datetime
output datetime:GetSecond()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetTimeZone">public system action GetTimeZone()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetTimeZone"><p>This action returns the timezone of the system. The timezone is reported as an offset of UTC. For example, Central Standard Time in the United States is UTC - 6.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the timezone offset from UTC.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetTimeZone"><em>Example Code:</em></span>
<pre class="code">DateTime datetime
output datetime:GetTimeZone()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetYear">public system action GetYear()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetYear"><p>This action returns the year represented by this DateTime object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the year</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetYear"><em>Example Code:</em></span>
<pre class="code">DateTime datetime
output datetime:GetYear()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsDaylightSavings">public system action IsDaylightSavings()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsDaylightSavings"><p>This action returns whether or not the current system is observing daylight savings time.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: whether or no daylight savings time is being observed</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsDaylightSavings"><em>Example Code:</em></span>
<pre class="code">DateTime datetime
boolean dst = datetime:IsDaylightSavings()
if dst
   output "Spring forward"
   else
   output "Fall back"
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetEpochTime:number">public system action SetEpochTime(number epochTime)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetEpochTime:number"><p>This action sets the date/time to be represented by this DateTime instance to the specified epochTime value. Once this function is called, this DateTime instance cannot be used to get the current date/time.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="epochTime"><strong>number</strong> <em>epochTime</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="epochTime">- the number of milliseconds that have elapsed since the epoch,
    returned by GetEpochTime().</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetEpochTime:number"><em>Example Code:</em></span>
<pre class="code">DateTime datetime
// Set this instance to the exact time of the epoch.
datetime:SetEpochTime(0)
output datetime:GetMonth() // will return 1 for January</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetTimeZone:integer">public system action SetTimeZone(integer timeZoneOffset)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetTimeZone:integer"><p>This action sets the time zone offset to be used when returning various date/time information to the user.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="timeZoneOffset"><strong>integer</strong> <em>timeZoneOffset</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="timeZoneOffset"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetTimeZone:integer"><em>Example Code:</em></span>
<pre class="code">DateTime datetime
datetime:SetTimeZone(0) // set time zone to UTC.
output "The current UTC hour is " + datetime:GetHour()</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>
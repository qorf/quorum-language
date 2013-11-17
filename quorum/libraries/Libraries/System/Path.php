<?php $classPageTitle = "Path"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.System.Path</h1>
<input type="hidden" id="classkey" value="Libraries.System.Path" /><h2> <span class="controllable" data-componentType="class-name">Class Path</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Path class is used to represent a path on the system. A path can be either absolute or relative. This class provides a consistent interface for manipulating paths, and provides a system that is more or less consistent throughout operating systems.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">// TODO</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPath">public action GetPath()</span></h3>

	<p>Get the set path. This may be either relative or absolute; to find out, use the IsPathRelative() and IsPathAbsolute() actions.</p><span class="controllable" data-componentType="action-description" data-actionkey="GetPath"><p>If no path is set, an InvalidPathError is thrown.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: the absolute or relative path set for this instance of Path.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPath"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.Path
Path p
p:SetRelativePath("./hello.txt")
output p:GetPath() // willoutput  "./hello.txt"
p:SetAbsolutePath("/Users/jeff")
output p:GetPath() // willoutput  "/Users/jeff"
p:SetAbsolutePath("C:\Windows\")
output p:GetPath() // willoutput  "C:\Windows\"</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsPathAbsolute">public action IsPathAbsolute()</span></h3>

	<p>Returns true if the set path is absolute. This action will raise an InvalidPathError if this instance of "Path" does not have a set path using either the SetPathAbsolute() or SetPathRelative() action.</p><p>An "absolute" path is a path that refers to an exact location on disk--that is, what the path refers to is independent of the current directory. As an example,</p><p>/hello.txt</p><p>is an absolute path, as on UNIX systems, it refers to a file under the root of the hard disk, outside of any particular directory. In addition, the path</p><p>C:\Program Files</p><p>is also absolute, as it refers to the "Program Files" directory on the "C" drive in Windows. If we were to write the path</p><p>Program Files</p><p>this would refer to the "Program Files" in the current directory, which could be on any drive, not necessarily just "C."</p><span class="controllable" data-componentType="action-description" data-actionkey="IsPathAbsolute"><p>If a path is not absolute, it is relative. For a description of relative paths, see the IsPathRelative() action in this class.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if the path is absolute; false otherwise.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsPathAbsolute"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.Path
Path p
p:SetAbsolutePath("C:\Windows")
output p:IsPathAbsolute() // willoutput  "true", as we called SetAbsolutePath
p:SetAbsolutePath("/Users/jeff") // on Mac and Unix/Linux systems, '/' means root of file system.
output p:IsPathAbsolute() // willoutput  "true", as we called SetAsolutePath</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsPathRelative">public action IsPathRelative()</span></h3>

	<p>Returns true if the set path is relative. A "relative" path is a path that does not refer to an exact location--that is, what it refers to depends on the current directory our application is in. As an example, the path</p><p>./hello.txt</p><p>is relative, as is</p><p>foo.txt</p><p>as these both refer to files in the current directory. In addition, the path</p><p>images/bar.png</p><p>is also relative, as it refers to a file in the "images" directory under the current directory.</p><span class="controllable" data-componentType="action-description" data-actionkey="IsPathRelative"><p>If a path is not relative, it is absolute. For a description of absolute paths, see the IsPathAbsolute() action in this class.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if the path is relative; false otherwise.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsPathRelative"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.Path
Path p
p:SetRelativePath("./hello.txt")
output p:IsPathRelative() // willoutput  "true", as we called SetRelativePath</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAbsolutePath:text">public action SetAbsolutePath(text path)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAbsolutePath:text"><p>Set an absolute path. See IsAbsolutePath() for an explanation of absolute paths. If the given path is not absolute, an InvalidPathError will be raised.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="path"><strong>text</strong> <em>path</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="path">the path to set</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAbsolutePath:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.Path
Path p
p:SetAbsolutePath("C:\Windows")
p:SetAbsolutePath("/Users/jeff") // on Mac and Unix/Linux systems, '/' means root of file system.</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetRelativePath:text">public action SetRelativePath(text path)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetRelativePath:text"><p>Set a relative path. See IsRelativePath() for an explanation of relative paths. If the given path is not relative, an InvalidPathError will be raised.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="path"><strong>text</strong> <em>path</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="path">the path to set</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetRelativePath:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.Path
Path p
p:SetRelativePath("./hello.txt") // in current directory
p:SetRelativePath("../../hello.txt") // go 2 directories up from our current directory
p:SetRelativePath("hello.txt") // in current directory</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>
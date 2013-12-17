<?php
$classPageTitle = 'The Quorum Standard Library';
include('static/templates/pageheader.template.php'); ?>
		<div class="hero-unit">
			<div class="hero-unit-container">
				<h1>The Quorum Standard Library</h1>
				<p>Quorum includes library classes like: 1) text-to-speech and audio playback, 2) container classes (e.g., arrays, lists, hash tables), 3) system classes, 4) classes for iCreate robotics, and 5) mathematics and random numbers. The standard library is expanded in each release.</p>
		</div>
</div>

<div class="container clearfix">
	<a href="/submitted_library_index.php" class="btn btn-primary pull-right" style="display:block;">View User-Submitted Experimental Libraries</a>
	<?php 
	if (isset($_COOKIE['username'])) {
		echo '<a href="/submit_library.php" class="btn btn-info pull-left">Submit a Library to the Quorum Standard Library</a>';
	}
	?>
	
</div><br /><br />
<ul class="index-grid">
<li class="grid-item grid-item-libraries-compute"><h2 class="index_package_title"><a href="#libraries-compute">Libraries.<br />Compute</a></h2>
<i></i>
<li class="grid-item grid-item-libraries-containers"><h2 class="index_package_title"><a href="#libraries-containers">Libraries.<br />Containers</a></h2>
<i></i>
<ul class="grid-sublist"><li class="sublist-item">Libraries.Containers.Blueprints</li><li class="sublist-item">Libraries.Containers.Support</li></ul><li class="grid-item grid-item-libraries-language"><h2 class="index_package_title"><a href="#libraries-language">Libraries.<br />Language</a></h2>
<i></i>
<ul class="grid-sublist"><li class="sublist-item">Libraries.Language.Errors</li><li class="sublist-item">Libraries.Language.Support</li><li class="sublist-item">Libraries.Language.Types</li></ul><li class="grid-item grid-item-libraries-robots"><h2 class="index_package_title"><a href="#libraries-robots">Libraries.<br />Robots</a></h2>
<i></i>
<li class="grid-item grid-item-libraries-sound"><h2 class="index_package_title"><a href="#libraries-sound">Libraries.<br />Sound</a></h2>
<i></i>
<li class="grid-item grid-item-libraries-system"><h2 class="index_package_title"><a href="#libraries-system">Libraries.<br />System</a></h2>
<i></i>
<ul class="grid-sublist"><li class="sublist-item">Libraries.System.Blueprints</li></ul><li class="grid-item grid-item-libraries-web"><h2 class="index_package_title"><a href="#libraries-web">Libraries.<br />Web</a></h2>
<i></i>
</ul>

<table class="table"><a id=""></a><table id="table-" class="table index-package">
<tr><th><h3 class="index_package_title">Default Package</h3></th></tr>
	<tr class="packages">
		<tr><td class="primary"><a href="Main.php">Main</a>: </td></tr>
	</table>
<a id="libraries-compute"></a><table id="table-libraries-compute" class="table index-package">
<tr><th><h3 class="index_package_title">Libraries.Compute</h3></th></tr>
	<tr class="packages">
		<tr><td class="primary"><a href="Libraries/Compute/BitwiseOperations.php">BitwiseOperations</a>: The BitwiseOperations class provides a way for a programmer to conduct 
bitwise operations on integer values. Common operations include bitwise and,
bitwise or, shift operators,  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Compute/Math.php">Math</a>: The Math class has a number of math actions you might find on a scientific 
calculator. For example, this class has Sine, Tangent, and Cosine actions, as
well as a few different ro ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Compute/Random.php">Random</a>: The Random class permits generation of pseudorandom numbers that can be used
for a variety of applications.</td></tr>
	</table>
<a id="libraries-containers"></a><table id="table-libraries-containers" class="table index-package">
<tr><th><h3 class="index_package_title">Libraries.Containers</h3></th></tr>
	<tr class="packages">
		<tr><td class="primary"><a href="Libraries/Containers/Array.php">Array</a>: The Array class is a data structure that stores items in contiguous memory. An
item is typically stored and accessed through an index or location. This location
always starts at 0, ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/HashTable.php">HashTable</a>: The HashTable class is a data structure that stores and allows access to items
through the use of a key. In the hash table keys and values are paired. Some 
basic examples and ex ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/List.php">List</a>: The List class is a data structure that stores items in nodes. Each of these
nodes stores an item and a reference to the next and previous node in the
sequence. Because of this, th ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Queue.php">Queue</a>: The Queue class is a data structure that stores items as if the items were in 
a line, or "Queue." The first items that go into the Queue are the first items 
to be removed from th ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Stack.php">Stack</a>: The Stack class is a data structure that stores items as if you were "stacking"
 them. It adds items to the top of the Stack, and when an item is requested to 
be removed, the top  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Table.php">Table</a>: The Table class is a data structure that stores items in contiguous memory. An
item is typically stored and accessed through an index or location(row 
and column). This location al ...</td></tr>
	</table>
<a id="libraries-containers-blueprints"></a><table id="table-libraries-containers-blueprints" class="table index-package">
<tr><th><h3 class="index_package_title">Libraries.Containers.Blueprints</h3></th></tr>
	<tr class="packages">
		<tr><td class="primary"><a href="Libraries/Containers/Blueprints/Addable.php">Addable</a>: Inheriting from the Addable class provides a basic blueprint for adding
and removing something from an object.</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Blueprints/ArrayBlueprint.php">ArrayBlueprint</a>: Inheriting from the ArrayBlueprint class provides a basic blueprint for a
array data structure. This array has a flag to indicate wither the array is
dynamic or not. A dynamic arra ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Blueprints/Container.php">Container</a>: Inheriting from the Containers class provides a basic blueprint for container
objects.</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Blueprints/Copyable.php">Copyable</a>: Inheriting from the Copyable class provides a basic blueprint for copying
a particular object.</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Blueprints/HashTableBlueprint.php">HashTableBlueprint</a>: Inheriting from the HashTableBlueprint class provides a basic blueprint for a
hash table data structure.</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Blueprints/Indexed.php">Indexed</a>: Inheriting from the Indexed class provides a basic blueprint for indexed
objects.</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Blueprints/Iterative.php">Iterative</a>: Inheriting from the Iterative class provides a basic blueprint for accessing
an iterator.</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Blueprints/Iterator.php">Iterator</a>: Inheriting from the Iterator class provides a basic blueprint for iterating
over an item.</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Blueprints/KeyedAddable.php">KeyedAddable</a>: Inheriting from the KeyedAddable class provides a basic blueprint for adding
and removing from an KeyedAddable object.</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Blueprints/KeyedIterative.php">KeyedIterative</a>: Inheriting from the KeyedIterative class provides a basic blueprint for getting
iterators for keyed objects.</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Blueprints/ListBlueprint.php">ListBlueprint</a>: Inheriting from the ListBlueprint class provides a basic blueprint for a
linked list data structure.</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Blueprints/QueueBlueprint.php">QueueBlueprint</a>: Inheriting from the QueueBlueprint class provides a basic blueprint for a
queue data structure.</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Blueprints/StackBlueprint.php">StackBlueprint</a>: Inheriting from the StackBlueprint class provides a basic blueprint for a
stack data structure.</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Blueprints/TableBlueprint.php">TableBlueprint</a>: Inheriting from the TableBlueprint class provides a basic blueprint for a
2D array data structure. This table has a flag to indicate wither the array is
dynamic or not. A dynamic t ...</td></tr>
	</table>
<a id="libraries-containers-support"></a><table id="table-libraries-containers-support" class="table index-package">
<tr><th><h3 class="index_package_title">Libraries.Containers.Support</h3></th></tr>
	<tr class="packages">
		<tr><td class="primary"><a href="Libraries/Containers/Support/ArrayIterator.php">ArrayIterator</a>: The ArrayIterator class for Arrays, enables iteration for the array data structure.
Generally, there is no need to use this class directly, as most users will only
need an instance ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Support/HashNode.php">HashNode</a>: </td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Support/HashTableIterator.php">HashTableIterator</a>: </td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Support/HashTableKeyIterator.php">HashTableKeyIterator</a>: </td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Support/HashTableValueIterator.php">HashTableValueIterator</a>: </td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Support/KeyedNode.php">KeyedNode</a>: The KeyedNode class is a support class for keyed data structures such as the Tree.</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Support/ListIterator.php">ListIterator</a>: The ListIterator class for Lists, enables iteration for the list data structure.</td></tr>
		<tr><td class="primary"><a href="Libraries/Containers/Support/ListNode.php">ListNode</a>: The ListNode class is a support class for List data structure.</td></tr>
	</table>
<a id="libraries-language"></a><table id="table-libraries-language" class="table index-package">
<tr><th><h3 class="index_package_title">Libraries.Language</h3></th></tr>
	<tr class="packages">
		<tr><td class="primary"><a href="Libraries/Language/Object.php">Object</a>: The Object class is the basic building block of all classes. This means 
everything is an Object and some of it's basic functionality is available
to all classes.</td></tr>
	</table>
<a id="libraries-language-errors"></a><table id="table-libraries-language-errors" class="table index-package">
<tr><th><h3 class="index_package_title">Libraries.Language.Errors</h3></th></tr>
	<tr class="packages">
		<tr><td class="primary"><a href="Libraries/Language/Errors/CastError.php">CastError</a>: The CastError class is an error or exception that is thrown when there is
an error while casting values.</td></tr>
		<tr><td class="primary"><a href="Libraries/Language/Errors/DivideByZeroError.php">DivideByZeroError</a>: The DivideByZeroError class is an error or exception that is thrown when a
divide by zero operation is attempted.</td></tr>
		<tr><td class="primary"><a href="Libraries/Language/Errors/EndOfFileError.php">EndOfFileError</a>: The EndOfFileError class is an error or exception that is thrown when 
an attempt is made to read from a file when the end of the file has been reached.
For example, reading an emp ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Language/Errors/Error.php">Error</a>: The Error class is a general error or exception that has been thrown. All
errors inherit from Error, which means error can be used as a catch all
error used by the exception handli ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Language/Errors/FileNotFoundError.php">FileNotFoundError</a>: The FileNotFoundError class is an error or exception that is thrown when 
an attempt is made to access a file that was not found on the disk.</td></tr>
		<tr><td class="primary"><a href="Libraries/Language/Errors/InputOutputError.php">InputOutputError</a>: The InputOutputError class is an error or exception that is thrown when 
a problem is encountered during system or file input/output. The exact
nature of the error can vary dependi ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Language/Errors/InvalidArgumentError.php">InvalidArgumentError</a>: The InvalidArgumentError class is an error or exception that is thrown when 
an action is called with an argument that is considered invalid by the called
action.</td></tr>
		<tr><td class="primary"><a href="Libraries/Language/Errors/InvalidLocationError.php">InvalidLocationError</a>: The InvalidLocationError class is an error or exception that is thrown when 
an invalid memory location is accessed. For example attempting to access the
index 21 in an array that  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Language/Errors/InvalidPathError.php">InvalidPathError</a>: The InvalidPathError class is an error or exception that is thrown when
a path specified for a required operation is not valid. There may be many
reasons for this, including refe ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Language/Errors/OutOfBoundsError.php">OutOfBoundsError</a>: The OutOfBoundsError class is an error or exception that is thrown when 
a parameter is outside of the specified range for a function.
For example, if a function requires only posi ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Language/Errors/ParseError.php">ParseError</a>: The ParseError class is an error or exception that is thrown when there is
an error while parsing a string and converting it into another form.</td></tr>
		<tr><td class="primary"><a href="Libraries/Language/Errors/UndefinedObjectError.php">UndefinedObjectError</a>: The UndefinedObjectError class is an error or exception that is thrown when 
a method is called on an undefined object.</td></tr>
	</table>
<a id="libraries-language-support"></a><table id="table-libraries-language-support" class="table index-package">
<tr><th><h3 class="index_package_title">Libraries.Language.Support</h3></th></tr>
	<tr class="packages">
		<tr><td class="primary"><a href="Libraries/Language/Support/CompareResult.php">CompareResult</a>: The CompareResult class provides constants to represent results of a comparison.
The possible results include LARGER, EQUAL, SMALLER, and INVALID. Each corresponds
to an integer va ...</td></tr>
	</table>
<a id="libraries-language-types"></a><table id="table-libraries-language-types" class="table index-package">
<tr><th><h3 class="index_package_title">Libraries.Language.Types</h3></th></tr>
	<tr class="packages">
		<tr><td class="primary"><a href="Libraries/Language/Types/Boolean.php">Boolean</a>: The Boolean class is the object representation of the primitive type boolean.</td></tr>
		<tr><td class="primary"><a href="Libraries/Language/Types/Integer.php">Integer</a>: The Integer class is the object representation of the primitive type integer.</td></tr>
		<tr><td class="primary"><a href="Libraries/Language/Types/Number.php">Number</a>: The Number class is the object representation of the primitive type number.</td></tr>
		<tr><td class="primary"><a href="Libraries/Language/Types/Text.php">Text</a>: The Text class is the object representation of the primitive type text.</td></tr>
	</table>
<a id="libraries-robots"></a><table id="table-libraries-robots" class="table index-package">
<tr><th><h3 class="index_package_title">Libraries.Robots</h3></th></tr>
	<tr class="packages">
		<tr><td class="primary"><a href="Libraries/Robots/AnalogSensor.php">AnalogSensor</a>: The Analog Sensor class represents a physical analog sensor. Each senor has a 
designation, an integer, that says which port it is connected to(e.g., 0 to 7). 
The analog sensor  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Robots/Button.php">Button</a>: The Button class represents the different touch screen buttons and the Black button 
present on the controller. 
There are seven different buttons present. The A button, B button ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Robots/Connectable.php">Connectable</a>: This is a parent class representing the types of objects, usually sensors
    that are able to be connected to the robot. This class has only two
    blueprint methods, for getti ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Robots/Controller.php">Controller</a>: The Controller class represents the physical Botball Controller. 
Several functions have been created to assist programmers in creating programs 
that meet the basic Botball requ ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Robots/DigitalSensor.php">DigitalSensor</a>: The Digital Sensor class represents a physical digital sensor. Each senor has a 
designation, an integer, that says which port it is connected to(e.g., 8 to 15). 
Once the sensor ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Robots/Motor.php">Motor</a>: The Motor class represents a physical motor. Each motor has a designation, 
an integer, that says which motor it is (e.g., 0, 1, 2 or 3). In order to use
a motor we need to set con ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Robots/Robot.php">Robot</a>: The Robot class represents a physical iRobot Create. iRobot Create is a primitive
robot or we can say it is a complete robot development kit that allows you to 
program some fund ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Robots/Servo.php">Servo</a>: The Servo class represents a physical servo. 
Each servo has a designation, 
an integer, that says which port this servo is connected to (e.g., 0, 1, 2 or 3). 
In order to use a ...</td></tr>
	</table>
<a id="libraries-sound"></a><table id="table-libraries-sound" class="table index-package">
<tr><th><h3 class="index_package_title">Libraries.Sound</h3></th></tr>
	<tr class="packages">
		<tr><td class="primary"><a href="Libraries/Sound/Audio.php">Audio</a>: The Audio class provides basic functionality for both playing and recording
sound files on the system. To use the audio class, we must first instantiate an
object of type Audio,  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Sound/Chord.php">Chord</a>: This class represents a chord. A chord is a collection of notes that are
    played together, at the same time.</td></tr>
		<tr><td class="primary"><a href="Libraries/Sound/Instrument.php">Instrument</a>: This class represents a MIDI instrument. A MIDI instrument consists of a
    number and a name. For a complete list of MIDI instruments specified by the
    General MIDI standard,  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Sound/Music.php">Music</a>: This class generates music from the Music Instrument Digital Interface
    (MIDI) standard. This class can be used to play invidual notes and
    individual chords, as well as used ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Sound/MusicEvent.php">MusicEvent</a>: This class represents an event in a music track. This is the top level
    interface describing anything that can be put on a music track, such as
    Notes and Chords.

    This c ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Sound/Note.php">Note</a>: This class represents a single note to be played.</td></tr>
		<tr><td class="primary"><a href="Libraries/Sound/Playable.php">Playable</a>: This class represents any kind of playable event in a music track. Playable
    events include any event that will sound one or more notes on the track.

    A Playable object has  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Sound/Speech.php">Speech</a>: The speech class does effectively the same thing as the say command in Quorum, 
except that it provides for more advanced functionality. Specifically, by
using the speech class, yo ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Sound/Track.php">Track</a>: This class represents a track. A track is a series of notes and chords. A collection
    of tracks makes up a song. When tracks are played, all tracks begin at the exact same time.</td></tr>
	</table>
<a id="libraries-system"></a><table id="table-libraries-system" class="table index-package">
<tr><th><h3 class="index_package_title">Libraries.System</h3></th></tr>
	<tr class="packages">
		<tr><td class="primary"><a href="Libraries/System/Console.php">Console</a>: The Console is a helper class that represents printing and input windows. In 
addition to these helper methods, the console class also allows the user to
retrieve any console argum ...</td></tr>
		<tr><td class="primary"><a href="Libraries/System/DateTime.php">DateTime</a>: The DateTime class is used to gather information about date and time on the system,
or from a specified date/time. On creation, this object will return information
pertaining to  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/System/File.php">File</a>: The File class is used to access and gather information about files on disk.
This class can represent either a file or a directory, and provides actions for
both manipulation and ...</td></tr>
		<tr><td class="primary"><a href="Libraries/System/FileRandomAccess.php">FileRandomAccess</a>: Ths class represents a "random access" file reader/writer. It is the standard
file random access reader/writer used in Quorum. By "random access," we mean
that it is possible to  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/System/FileReader.php">FileReader</a>: TODO: Check to make sure a file is opened.

This class represents a sequential file reader. It is the standard file reader
used in Quorum. By "sequential," we mean that it is po ...</td></tr>
		<tr><td class="primary"><a href="Libraries/System/FileWriter.php">FileWriter</a>: This class represents a sequential file writer. It is the standard file writer
used in Quorum. By "sequential," we mean that it is possible to only move
forward in the file. Once ...</td></tr>
		<tr><td class="primary"><a href="Libraries/System/Path.php">Path</a>: The Path class is used to represent a path on the system. A path can be either
absolute or relative. This class provides a consistent interface for
manipulating paths, and provid ...</td></tr>
		<tr><td class="primary"><a href="Libraries/System/Properties.php">Properties</a>: The properties class is designed to gather information about the system
that the user is running. Currently, it supports a general property action, 
GetProperty, which can gather a ...</td></tr>
		<tr><td class="primary"><a href="Libraries/System/StackTraceItem.php">StackTraceItem</a>: The StackTraceItem is a helper class that represents an item on the call stack. It 
is used by the Error classes to generate stack traces.</td></tr>
	</table>
<a id="libraries-system-blueprints"></a><table id="table-libraries-system-blueprints" class="table index-package">
<tr><th><h3 class="index_package_title">Libraries.System.Blueprints</h3></th></tr>
	<tr class="packages">
		<tr><td class="primary"><a href="Libraries/System/Blueprints/FileRandomAccessBlueprint.php">FileRandomAccessBlueprint</a>: Inheriting from FileRandomAccessBlueprint provides a standard interface for
reading and writing files in a "random access" order. In this context,
"random access" implies that th ...</td></tr>
		<tr><td class="primary"><a href="Libraries/System/Blueprints/FileReaderBlueprint.php">FileReaderBlueprint</a>: Inheriting from FileReaderBlueprint provides a standard interface for reading
files in sequential order.

See the FileReader class for the standard Quorum implementation of this ...</td></tr>
		<tr><td class="primary"><a href="Libraries/System/Blueprints/FileWriterBlueprint.php">FileWriterBlueprint</a>: Inheriting from FileWriterBlueprint provides a standard interface for writing
files in sequential order.

See the FileWriter class for the standard Quorum implementation of this ...</td></tr>
	</table>
<a id="libraries-web"></a><table id="table-libraries-web" class="table index-package">
<tr><th><h3 class="index_package_title">Libraries.Web</h3></th></tr>
	<tr class="packages">
		<tr><td class="primary"><a href="Libraries/Web/Abbreviation.php">Abbreviation</a>: The Abbrevation class represents HTML's (Hypertext Markup Language) abbr tag which
is used to create tooltips of the full text of an abbrevation.
You can find more information ab ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Area.php">Area</a>: The Area class represents HTML's (Hypertext Markup Language) area tag which is 
used to make clickable areas on an ImageMap class.
You can find more information about this tag at ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Article.php">Article</a>: The Article class represents HTML's (Hypertext Markup Language) article tag which is
used to group text for self contained content.
You can find more information about this tag a ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Aside.php">Aside</a>: The Aside class represents HTML's (Hypertext Markup Language) aside tag which is
used to define content aside from the content it is placed in.
You can find more information abou ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Attribute.php">Attribute</a>: The Attribute class represents a property of a particular tag in the 
web libraries. For example, the Hypertext Markup Language (HTML) 5 standard,
we might define an attribute th ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/AttributeAccepter.php">AttributeAccepter</a>: The AttributeAccepter class is designed as a helper to ease adding and removing
attributes from particular WebTag objects. While there is no harm in creating
an object of this ty ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Attributes.php">Attributes</a>: The Attributes class stores a number of attribute objects. Effectively, 
this class is just a wrapper for the HashTable class, adding an action
related to automatically printing  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Audio.php">Audio</a>: The Audio class represents HTML's (Hypertext Markup Language) audio tag which is 
used to add an audio player/content to a page.
You can find more information about this tag at:  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Base.php">Base</a>: Base should only be used once and only in the WebPageHeader. The purpose of this
is to set a base location for the browser to look for links that will be put 
into the page.

T ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/BiDirectionalIsolation.php">BiDirectionalIsolation</a>: The BiDirectionalIsolation class represents HTML's (Hypertext Markup Language) 
bdi tag which is used to isloate text a piece of text that may be formatted in
a different directi ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/BiDirectionalOverride.php">BiDirectionalOverride</a>: The BiDirectionalOverride class represents HTML's (Hypertext Markup Language) 
bdo tag which is used to set the direction that should be displayed

You can find more information ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/BlockQuote.php">BlockQuote</a>: The BlockQuote class represents HTML's (Hypertext Markup Language) 
blockquote tag which is used to specify a larger amount of text that is quoted
from a source. You can set the  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Body.php">Body</a>: The Body class represents HTML's (Hypertext Markup Language) body tag which 
contains the majority of the elements that a user sees on screen. Since the 
Body class is a WebTag,  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Bold.php">Bold</a>: The Bold class represents HTML's (Hypertext Markup Language) b tag which 
specifies bold text and should only be used as a last resort. A better approach 
would be to use CSS's " ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Button.php">Button</a>: The Button class represents HTML's (Hypertext Markup Language) button tag which 
is a button control. 
You can find more information about this tag at: 
    <a href="http://www. ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/CalculationOutput.php">CalculationOutput</a>: The CalculationOutput class represents HTML's (Hypertext Markup Language) output 
tag which is used display a calculation that was preformed by a script.
You can find more inform ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Canvas.php">Canvas</a>: The Canvas class represents HTML's (Hypertext Markup Language) canvas tag which is
used to place a canvas on the page that is used as a container for grapics.
A canvas should hav ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Cite.php">Cite</a>: The Cite class represents HTML's (Hypertext Markup Language) cite tag which is
used to italicize the title of cited information being displayed.
You can find more information abo ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/CodeBlock.php">CodeBlock</a>: The CodeBlock class represents HTML's (Hypertext Markup Language) code tag which is
used to define code. It is recommended that CodeBlock be used in PreformattedText 
to keep the ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Column.php">Column</a>: The Column class represents HTML's (Hypertext Markup Language) col tag which allows an 
entire column to be formatted and controlled from one element instead of each 
cell in a t ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/ColumnGroup.php">ColumnGroup</a>: The ColumnGroup class represents HTML's (Hypertext Markup Language) colgroup tag 
which allows one or more columns to be selected for formatting.
Columns should be added to the C ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Command.php">Command</a>: This is only supported in Internet Explorer 9 (IE9).
Only features that are supported have been added.

The Command class represents HTML's (Hypertext Markup Language) command t ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Comment.php">Comment</a>: The Comment represents HTML's (Hypertext Markup Language) !-- tag which allows 
a comment to be added to the webpage. 
You can find more information about this tag at: 
    <a h ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Definition.php">Definition</a>: The Definition class represents HTML's (Hypertext Markup Language) dd tag which is
used to make an indented definition for an item in a DefinitionList class.
You can find more in ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/DefinitionList.php">DefinitionList</a>: The DefinitionList class represents HTML's (Hypertext Markup Language) dl tag 
which is used to make a list for Definitions and DefinitionTerms.
You can find more information abo ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/DefinitionListTerm.php">DefinitionListTerm</a>: The DefinitionListTerm class represents HTML's (Hypertext Markup Language) dt tag which is
used to ittalcize a word that will be defined by a following definition.
This is meant  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/DefinitionTerm.php">DefinitionTerm</a>: The DefinitionTerm class represents HTML's (Hypertext Markup Language) dfn tag which is
used to ittalcize a word that will be defined by a following definition.
This is meant to  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Deleted.php">Deleted</a>: The Deleted class represents HTML's (Hypertext Markup Language) del tag which is
used to show that text has been deleted from a document. This strikes through the 
deleted text.  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Details.php">Details</a>: The Details class represents HTML's (Hypertext Markup Language) details tag which is
used to show or hide additional details about an item with a collapsible view. 

The Summary ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Division.php">Division</a>: The Division class represents HTML's (Hypertext Markup Language) div tag which 
contains a section in the body of the webpage. It is a way to divide the component of
your webpage i ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/DocumentType.php">DocumentType</a>: This class represents a document type, as presented in the w3 web standard
    for web pages (HTML). By default, this class always defaults to the newest
    standard for the web ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Embed.php">Embed</a>: The Embed class represents HTML's (Hypertext Markup Language) embed tag which is
used to define a container for an application or interactive content. 
You can find more informat ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/EmbedContent.php">EmbedContent</a>: The EmbedContent class represents HTML's (Hypertext Markup Language) object tag which is
used to embed content like a picture, video, pdf, flash content, etc.
You can find more i ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Emphasize.php">Emphasize</a>: The Emphasize class represents HTML's (Hypertext Markup Language) em tag which is
used to emphasize/ittalcize text.
You can find more information about this tag at: 
        <a  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/EventAttributeAccepter.php">EventAttributeAccepter</a>: The EventAttributeAccepter is a helper class which is a subclass of all 
accepters classes. This includesAttributeAccepter, MouseAttributeAccepter, 
WindowAttributeAccepter, Form ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/FieldSet.php">FieldSet</a>: The FieldSet class represents HTML's (Hypertext Markup Language) fieldset tag 
which allows elements in a form to be grouped inside of a box.
You can find more information about  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Figure.php">Figure</a>: The Figure class represents HTML's (Hypertext Markup Language) figure tag which is
used to define self contained content like an image or diagram that can be used 
for reference. ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/FigureCaption.php">FigureCaption</a>: The FigureCaption class represents HTML's (Hypertext Markup Language) figcaption tag which is
used to add a discription to a Figure.
You can find more information about this tag  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Footer.php">Footer</a>: The Footer class represents HTML's (Hypertext Markup Language) footer tag which is
used to add information like the author or copyright to the end of the page.
You can find more  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Form.php">Form</a>: The Form class represents HTML's (Hypertext Markup Language) form tag which 
allows a form to be generated that accepts user input. The form can contain 
one or more FieldSet, In ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/FormAttributeAccepter.php">FormAttributeAccepter</a>: The FormAttributeAccepter class is designed as a helper to ease adding and removing
attributes from particular WebTag objects belonging to a form. While there is 
no harm in crea ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/GlobalAttributeAccepter.php">GlobalAttributeAccepter</a>: The GlobalAttributeAccepter class is designed as a helper to ease adding and removing
attributes from particular WebTag objects. While there is 
no harm in creating an object of  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Header.php">Header</a>: The Header class represents HTML's (Hypertext Markup Language) header tag which 
contains the header information for a section or document. 
You can find more information about t ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Heading.php">Heading</a>: The Heading class represents HTML's (Hypertext Markup Language) h1-h6 (headings) 
tag which is used add titles to a page.

Headings vary in size from a scale of 1-6. Where 1 is  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/HeadingGroup.php">HeadingGroup</a>: The HeaderGroup class represents HTML's (Hypertext Markup Language) hgroup tag which is
used to group mulitple Headings.
You can find more information about this tag at: 
       ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Highlight.php">Highlight</a>: The Highlight class represents HTML's (Hypertext Markup Language) mark tag which is
used to highlight text.
You can find more information about this tag at: 
        <a href="ht ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Image.php">Image</a>: The Image class represents HTML's (Hypertext Markup Language) img tag which is
used to add an image to the page.
You can find more information about this tag at: 
        <a hre ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/ImageMap.php">ImageMap</a>: The ImageMap class represents HTML's (Hypertext Markup Language) map tag which is
used to make an image with different clickable areas. These areas are defined by 
the Area class ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/InlineFrame.php">InlineFrame</a>: The InlineFrame class represents HTML's (Hypertext Markup Language) iframe tag which is
used to add an another page into the current webpage.
You can find more information about  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Input.php">Input</a>: The Input class represents HTML's (Hypertext Markup Language) input tag which 
defines an input control within a Form. The type of control is defined by the 
type attribute. 
Yo ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Inserted.php">Inserted</a>: The Inserted class represents HTML's (Hypertext Markup Language) ins tag which is
used to show text that has been inserted into a document. This underlines the 
inserted text. Ci ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Italic.php">Italic</a>: The Italic class represents HTML's (Hypertext Markup Language) i tag which 
italisizes a webpages text group. 
You can find more information about this tag at: 
    <a href="htt ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/KeyBoardInput.php">KeyBoardInput</a>: The KeyBoardInput class represents HTML's (Hypertext Markup Language) kbd tag which is
used to define keyboard input.
You can find more information about this tag at: 
        < ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/KeyGenerator.php">KeyGenerator</a>: The KeyGenerator class represents HTML's (Hypertext Markup Language) keygen tag which is
used to make a key-pair generator for forms. When the form is submitted two keys 
are cre ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/KeyboardAttributeAccepter.php">KeyboardAttributeAccepter</a>: The KeyboardAttributeAccepter class is designed as a helper to ease adding and removing
keyboard event attributes from particular WebTag objects. While there is 
no harm in creat ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Label.php">Label</a>: The Label class represents HTML's (Hypertext Markup Language) label tag which 
specifies labeling text for an input tag. This label can be bound to an input 
tag by either settin ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/LanguageCode.php">LanguageCode</a>: This class represents the language HTML language codes.</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Legend.php">Legend</a>: The Legend class represents HTML's (Hypertext Markup Language) legend tag which is
used to add a caption to the FieldSet class.
You can find more information about this tag at:  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/LineBreak.php">LineBreak</a>: The LineBreak class represents HTML's (Hypertext Markup Language) br tag which 
creates a new line. 
You can find more information about this tag at: 
    <a href="http://www.w3 ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Link.php">Link</a>: The Link class represents HTML's (Hypertext Markup Language) a tag which 
is a way to add links to other web pages to your page.
You can find more information about this tag at:  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/ListItem.php">ListItem</a>: The ListItem class represents HTML's (Hypertext Markup Language) li tag which is
used to define a list item that can be used in OrderedList, UnorderedList, and 
MenuList classes. ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/ListOptions.php">ListOptions</a>: The ListOptions class represents HTML's (Hypertext Markup Language) datalist tag which is
used to contain pre-defined Options for an Input class. 

This is used on the input by  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/MediaAttributeAccepter.php">MediaAttributeAccepter</a>: The MediaAttributeAccepter class is designed as a helper to ease adding and removing
keyboard event attributes from particular WebTag objects. While there is 
no harm in creating ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Menu.php">Menu</a>: The Menu class represents HTML's (Hypertext Markup Language) menu tag which is
used to add a menu of controls to the page. **This is not currently supported in 
any major browser ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/MetaData.php">MetaData</a>: The MetaData class represents HTML's (Hypertext Markup Language) meta tag which 
is used to store data about the webpage that is not displayed. An example might be
the author nam ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/MouseAttributeAccepter.php">MouseAttributeAccepter</a>: The MouseAttributeAccepter class is designed as a helper to ease adding and removing
mouse event attributes from particular WebTag objects. While there is 
no harm in creating an ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/NavigationLink.php">NavigationLink</a>: The NavigationLink class represents HTML's (Hypertext Markup Language) nav tag which is
used to define a section of navigation links. This is meant for having a larger
group of l ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/NoRuby.php">NoRuby</a>: The NoRuby class represents HTML's (Hypertext Markup Language) rp tag which is
used to show different content if browser does not support the Ruby annotations.
You can find more  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/NoScript.php">NoScript</a>: The NoScript class represents HTML's (Hypertext Markup Language) noscript tag which is
used to show different content if a user is not allowing for scripts to be run 
or the brow ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Option.php">Option</a>: The Option class represents HTML's (Hypertext Markup Language) option tag which 
is used to define an option item inside a select or datalist. 
You can find more information abou ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/OptionGroup.php">OptionGroup</a>: The OptionGroup class represents HTML's (Hypertext Markup Language) optiongroup 
tag which is used to group options in a select or data list. 
You can find more information about ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/OrderedList.php">OrderedList</a>: The OrderedList class represents HTML's (Hypertext Markup Language) ol tag which is
used to create an ordered list that can be ordered numerically or alphabetically.
You can find ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/OwnerAddress.php">OwnerAddress</a>: The OwnerAddress class represents HTML's (Hypertext Markup Language) address tag 
which defines the contact information for the author/owner of a document.
You can find more info ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/PageBreak.php">PageBreak</a>: The PageBreak class represents HTML's (Hypertext Markup Language) hr tag which is
used to add a line through the page showing a break in content.
You can find more information ab ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Paragraph.php">Paragraph</a>: The Paragraph class represents HTML's (Hypertext Markup Language) p tag which is
used to group text in paragraph format.
You can find more information about this tag at:
<a href="h ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Parameter.php">Parameter</a>: The Parameter class represents HTML's (Hypertext Markup Language) param tag which is
used to define parameter to pass to plugins that are in an EmbedContent class.
You can find m ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/PerformanceGauge.php">PerformanceGauge</a>: The PerformanceGauge class represents HTML's (Hypertext Markup Language) meter tag which is
used to display a bar gauge that shows performace. This is not to show progress, 
use  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/PreformattedText.php">PreformattedText</a>: The PreformattedText class represents HTML's (Hypertext Markup Language) pre tag which is
used to preserve line breaks and spaces that are in the text.
You can find more informat ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/ProgressBar.php">ProgressBar</a>: The ProgressBar class represents HTML's (Hypertext Markup Language) progress tag which is
used to show a progress bar to display the progress of a task.
You can find more informa ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Quote.php">Quote</a>: The Quote class represents HTML's (Hypertext Markup Language) q tag which is
used to add a short quote. The quotation marks will be added to the text.
You can find more informati ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Ruby.php">Ruby</a>: The Ruby class represents HTML's (Hypertext Markup Language) ruby tag which is
used to Ruby annotations are used for East Asian typography, to show the 
pronunciation of East Asi ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/RubyContext.php">RubyContext</a>: The RubyContext class represents HTML's (Hypertext Markup Language) rt tag which is
used to give a pronunciation of characters in a ruby annotation. 
You can find more informatio ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/SampleOutput.php">SampleOutput</a>: The SampleOutput class represents HTML's (Hypertext Markup Language) samp tag which is
used to show sample output of a computer program.
You can find more information about this  ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Script.php">Script</a>: The Script class represents HTML's (Hypertext Markup Language) script tag which is
used to point to a client-side script (e.g. javascript). This can be used for
dynamic changes t ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Section.php">Section</a>: The Section class represents HTML's (Hypertext Markup Language) section tag which is
used to define sections in the page to seperate Headers, Footers, chapters of 
text, etc.
Yo ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Select.php">Select</a>: The Select class represents HTML's (Hypertext Markup Language) select tag which 
is a selection list of options or items. 
You can find more information about this tag at: 
<a h ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/SmallText.php">SmallText</a>: The SmallText class represents HTML's (Hypertext Markup Language) small tag which is
used to add smaller text for side comments or copyrights.
You can find more information about ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Source.php">Source</a>: The Source class represents HTML's (Hypertext Markup Language) source tag which is
used to specify multiple types of the same resource in case a browser does not 
support one typ ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Span.php">Span</a>: The Span class represents HTML's (Hypertext Markup Language) span tag which is
a tag used to group text. Span does not modify the format directly but its
attributes can be used t ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/StrikeThrough.php">StrikeThrough</a>: The StrikeThrough class represents HTML's (Hypertext Markup Language) s tag which is
used to strike through text that is no longer correct.
You can find more information about th ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/StrongText.php">StrongText</a>: The StrongText class represents HTML's (Hypertext Markup Language) strong tag which is
used to bold text to show emphasis.
You can find more information about this tag at: 
     ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Style.php">Style</a>: The Style class represents HTML's (Hypertext Markup Language) style tag which is
used to define style or css information to the document.
You can find more information about this ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/StylesheetLink.php">StylesheetLink</a>: The Option class represents HTML's (Hypertext Markup Language) link tag which 
is a link established between the webpages and its css(stylesheet). 
You can find more information ab ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/SubscriptText.php">SubscriptText</a>: The SubscriptText class represents HTML's (Hypertext Markup Language) sub tag which is
used to add subscriptted text. 
You can find more information about this tag at: 
         ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Summary.php">Summary</a>: The Summary class represents HTML's (Hypertext Markup Language) summary tag which is
used to add a title to a Details class.
You can find more information about this tag at: 
   ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/SuperscriptText.php">SuperscriptText</a>: The SubscriptText class represents HTML's (Hypertext Markup Language) sup tag which is
used to add superscriptted text. 
You can find more information about this tag at: 
       ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/TableBody.php">TableBody</a>: The TableBody class represents HTML's (Hypertext Markup Language) tbody tag which is
used to group the main part of a table content. This will be displayed after the 
TableHeader ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/TableCaption.php">TableCaption</a>: The TableCaption must be added as the first thing in a WebTable. 
WebTable can only have one caption per table.

The TableCaption class represents HTML's (Hypertext Markup Langu ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/TableData.php">TableData</a>: The TableData class represents HTML's (Hypertext Markup Language) td tag which 
is often contained in a TableRow. A TableData object represents a general and singular
cell in a t ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/TableFooter.php">TableFooter</a>: The TableFooter class represents HTML's (Hypertext Markup Language) tfoot tag which is
used to footer content of a table. This will go after the TableBody.
You can find more info ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/TableHeader.php">TableHeader</a>: The TableHeader class represents HTML's (Hypertext Markup Language) thead tag which is
used to group the TableHeaderCells of a table. This will be displayed before the 
TableBody ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/TableHeaderCell.php">TableHeaderCell</a>: The TableHeaderCell class represents HTML's (Hypertext Markup Language) th tag 
which is a header cell table element often contained in a TableRow. 
A TableHeader object represen ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/TableRow.php">TableRow</a>: The TableRow class represents HTML's (Hypertext Markup Language) option tag 
which is a row in HTMLs (Hypertext Markup Language) table element. A
TableRow can contain multiple Ta ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/TextArea.php">TextArea</a>: The TextArea class represents HTML's (Hypertext Markup Language) textarea tag 
which is a multi-line text input box that is capable of containing 
an unlimited number of caracter ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Time.php">Time</a>: The Time class represents HTML's (Hypertext Markup Language) time tag which is
used to add a time reference to times on the page. It does not change how the 
text is shown but is ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Title.php">Title</a>: The Title class represents HTML's (Hypertext Markup Language) title tag which 
is the title for a webpage.
You can find more information about this tag at:
<a href="http://www.w3sc ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Track.php">Track</a>: The Track class represents HTML's (Hypertext Markup Language) track tag which is
used to text tracks (subtitle or lyrics) to audio or video. This is currently 
not supported in a ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/UnderlineText.php">UnderlineText</a>: The UnderlineText class represents HTML's (Hypertext Markup Language) u tag which is
used to underline text. Can be used to point out misspelled words.
You can find more informat ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/UnorderedList.php">UnorderedList</a>: The UnorderedList class represents HTML's (Hypertext Markup Language) ul tag which is
used to add an unordered list to the page. Use ListItem to add to the list.
You can find mor ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Variable.php">Variable</a>: The Variable class represents HTML's (Hypertext Markup Language) var tag which is
used represent a variable in the text.
You can find more information about this tag at: 
       ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/Video.php">Video</a>: The Video class represents HTML's (Hypertext Markup Language) video tag which is
used to add a video to the page.
You can find more information about this tag at: 
        <a hr ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/WebGenerator.php">WebGenerator</a>: The WebGenerator class contains a single blueprint action designed for 
generating web content. Essentially, it is a helper parent class that any related
class will need to imple ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/WebPage.php">WebPage</a>: The WebPage class represents HTML's (Hypertext Markup Language) option tag which 
defines the document as an html document. 
You can find more information about this tag at: 
<a hr ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/WebPageHeader.php">WebPageHeader</a>: The WebPageHeader class represents HTML's (Hypertext Markup Language) head tag 
which is a container class for any WebTags that belong in the
web pages header. 
You can find more i ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/WebTable.php">WebTable</a>: The WebTable class represents HTML's (Hypertext Markup Language) table tag which 
is a class that builds a table. This table contains any number
of rows that have been added to t ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/WebTag.php">WebTag</a>: The WebTag class is a generic helper class which manages Hypertext Markup Language 
(HTML) 5 tags. Tags are stored in a list of WebTags, thus, allowing tags to
be nested within e ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/WindowAttributeAccepter.php">WindowAttributeAccepter</a>: The WindowAttributeAccepter class is designed as a helper to ease adding and removing
window event attributes from particular WebTag objects. While there is 
no harm in creating an ...</td></tr>
		<tr><td class="primary"><a href="Libraries/Web/WordBreak.php">WordBreak</a>: The WordBreak class represents HTML's (Hypertext Markup Language) wbr tag which is
used to add words that are ok to brake up when the page gets shrunk down.
You can find more inf ...</td></tr>

</table><?php include('static/templates/pagefooter.template.php'); ?>
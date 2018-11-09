[![GitHub license](https://img.shields.io/github/license/dcendents/android-maven-gradle-plugin.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.dcendents/android-maven-gradle-plugin.svg)](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22android-maven-gradle-plugin%22)
[![](https://jitpack.io/v/BrotherV/ToolsLibrary.svg)](https://jitpack.io/#BrotherV/ToolsLibrary)
# Tools Library
**An extended library from different widgets such as Layouts, Spinner and TextView which have more features than original version**

#### ExtendSpinner
You can add what ever you want only in xml file without writing any JAVA code. By defining ExtendedSpinner in xml you can easily have a fully supported rtl/ltr spinner with too many options.

#### ExtendTextView
You can easily add rtl/ltr font in xml file just same as fontFamily attribute in android oreo, but it works for all version of android.

#### ExtendConstraintLayout, ExtendCoordinatorLayout, ExtendFrameLayout, ExtendLinearLayout
You can insert objects in these layouts where yo want to move up and down when snackbar comes up and down. Also you can get the exaxt point of x and y when you use these layouts as parent.

#### FloatFrameLayout, FloatLinearLayout
if you want to hide/show a few widgets just like FloatActionButton when moving RecyclerView/ScrolView/ListView you can use these widgets.

## Usage

Step 1. Add it in your root build.gradle at the end of repositories:
```Groovy
allprojects{
	repositories{
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Step 2. Add the dependency
```Groovy
dependencies{
	     implementation 'com.github.BrotherV:ToolsLibrary:0.48'
}
```

## License
```
Copyright 2018 BrotherV

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


DIR=$(
  cd $(dirname "$0")
  cd ..
  pwd
)

DIR="$DIR/native/PlatformLib/"

echo "depends = Foundation
language = Objective-C
headers = PlatformLib.framework/Headers/PlatformLib.h PlatformLib.framework/Headers/PlatformLib-Swift.h
headerFilter = **
libraryPaths = $DIR -framework PlatformLib
compilerOpts = -F$DIR -fmodules -framework PlatformLib
linkerOpts = -F$DIR
" > ./src/nativeInterop/cinterop/PlatformLib.def
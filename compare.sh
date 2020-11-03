#!/bin/sh

# This script is responsible for setting up the environment, retrieving the
# screenshots from and emulator and running comparisons between the screenshots
# in order to define if a changed occured or not. If any change occured we will
# generate an image that contains the differences. The way that we take screenshots
# is out of the scope. This script assumes that the screenshots were taken somehow.
# We will use Sherlock library to do so, but no need to be bound on it. The roadmap
# is to split this script to four different parts:
#
# 1) set up
# 2) pull
# 3) compare
# 4) pull and compare
#
# Also, we will create a gradle plugin in order to distribute it easily and make it
# more configurable (e.g change paths and so on)

# We need to ensure that we have two arguments. The first one would be the module's
# name and the second one would be be the application's id.
if [ "$#" -ne "2" ]
  then
    echo 'You should provide module name and application id'
    exit 128
fi

MODULE_NAME=$1
APPLICATION_ID=$2

echo $'*** Set up environment ***'

# Install ffmpeg based on operating system. We need to optimize this
# and avoid installing it again and again. We can easily understan in
# which operating system we are running based on uname.
# https://en.wikipedia.org/wiki/Uname
uname="$(uname -s)"
case "${uname}" in
    Linux*)     sudo apt install ffmpeg;;
    Darwin*)    brew install ffmpeg;;
    *)          echo="Not supported OS $uname"
esac

# Add a directory in which we are going to pull the
pullPath=$(pwd)"/$MODULE_NAME/pulled-screenshots"
mkdir $pullPath

# Add a directory in which we are going to pull the
resultsPath=$pullPath'/screenshot-testing-results'
mkdir $resultsPath

echo $'\n*** Start pulling files from device ***'
adb shell find "/storage/emulated/0/Android/data/$APPLICATION_ID/files" -type f | while read file; do adb pull $file $pullPath; done

echo $'\n*** Start comparing screenshots ***'

# The following text is being printed by ffmpeg when there
# are no difference between two images. We will use it in
# order to skip creating image diffs.
success='n:1 R:1.000000 G:1.000000 B:1.000000 All:1.000000 (inf)'

# According to FFMpeg we must use the following graph in our
# filter, in order to compare two images without any output.
compareGraph='-filter_complex ssim=stats_file=- -f null -'

#############################################################
#         Configuration for generating diff image           #
#############################################################
colorbalance='colorbalance=rs=1:gs=-1:bs=-1:rm=1:gm=-1:bm=-1:rh=1:gh=-1:bh=-1'
# Similarity is the threshold to identify image differences and can take values between [0.01 - 1.0]
# Blend percentage for the differential pixels can take values between [0.0 - 1.0]
colorkey='colorkey=white:similarity=0.01:blend=1.0'
# Opacity of the reference image can take values between [0.0 - 1.0]
overlay='[0]format=rgba,colorchannelmixer=aa=0.1[bg];[bg][diff]overlay=format=rgb'

# According to FFMpeg we must use the following graph in our
# filter, in order to generate an image that contains the
# differences of the compared images.
generateDiffGraph="-filter_complex ssim=stats_file=-[ssim];[ssim][1]blend=all_mode=phoenix,format=rgba,$colorkey,$colorbalance[diff];$overlay"

for file in $pullPath'/'*; do
  if [ -f "$file" ]; then

    image1=$MODULE_NAME/screenshots/${file##*/}
    image2=$MODULE_NAME/pulled-screenshots/${file##*/}

    # Compare the two images
    result=$(ffmpeg -i $image1 -i $image2 $compareGraph)

    if [ "$result" == "$success" ]; then
      echo "${file##*/}: SUCCESS"
      continue
    fi

    echo "${file##*/}: FAIL"

    # Generates an image that contains the differences of the compared screenshots
    ffmpeg -i $image1 -i $image2 $generateDiffGraph -y ${file##*/} >/dev/null

    mv $(pwd)'/'${file##*/} $resultsPath/${file##*/}
  fi
done

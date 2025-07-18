//
//  ViroTextComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroTextComponentView : RCTViewComponentView

// Text content
- (void)setText:(nullable NSString *)text;

// Text styling
- (void)setFontFamily:(nullable NSString *)fontFamily;
- (void)setFontSize:(CGFloat)fontSize;
- (void)setFontWeight:(nullable NSString *)fontWeight;
- (void)setFontStyle:(nullable NSString *)fontStyle;
- (void)setColor:(nullable NSString *)color;

// Text layout
- (void)setWidth:(CGFloat)width;
- (void)setHeight:(CGFloat)height;
- (void)setTextAlign:(nullable NSString *)textAlign;
- (void)setTextAlignVertical:(nullable NSString *)textAlignVertical;
- (void)setTextLineBreakMode:(nullable NSString *)textLineBreakMode;
- (void)setTextClipMode:(nullable NSString *)textClipMode;
- (void)setMaxLines:(NSInteger)maxLines;

// 3D text properties
- (void)setExtrusionDepth:(CGFloat)extrusionDepth;
- (void)setOuterStroke:(nullable NSDictionary *)outerStroke;
- (void)setMaterials:(nullable NSArray<NSString *> *)materials;

@end

NS_ASSUME_NONNULL_END